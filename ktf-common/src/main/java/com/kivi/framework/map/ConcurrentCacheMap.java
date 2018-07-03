package com.kivi.framework.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class ConcurrentCacheMap<K, V> extends ConcurrentHashMap<K, V> {
    private static final Logger                  log           = LoggerFactory.getLogger(ConcurrentCacheMap.class);

    private static int                           TIMEOUT       = 60;
    private static int                           TICKS         = 100;
    private static int                           TICK_DURATION = 1;
    private static TimeUnit                      TIME_UNIT     = TimeUnit.SECONDS;

    private static int                           MAX_CAPACITY  = 10000;

    private static AtomicLong                    mapSize       = new AtomicLong(0L);

    private static ConcurrentLinkedDeque<Object> keyQueue      = new ConcurrentLinkedDeque<>();

    private static HashedWheelTimer              wheel         = null;

    private long                                 aliveTime     = 0;

    public ConcurrentCacheMap() {
        super();

    }

    /**
     * 构造函数
     * 
     * @param timeout
     *            数据超时时间
     * @param unit
     *            时间单位
     */
    public ConcurrentCacheMap( int timeout, TimeUnit unit ) {
        this(TICKS, TICK_DURATION, timeout, unit, MAX_CAPACITY);
    }

    /**
     * 构造函数
     * 
     * @param ticks
     *            时间轮的格子数
     * @param tickDuration
     *            时间伦每格的时间
     * @param timeout
     *            数据超时时间
     * @param unit
     *            时间单位
     */
    public ConcurrentCacheMap( int ticks, int tickDuration, int timeout, TimeUnit unit ) {
        this(ticks, tickDuration, timeout, unit, MAX_CAPACITY);
    }

    /**
     * 构造函数
     * 
     * @param ticks
     *            时间轮的格子数
     * @param tickDuration
     *            时间伦每格的时间
     * @param timeout
     *            数据超时时间
     * @param unit
     *            时间单位
     * @param maxCapacity
     *            map最大容量
     */
    public ConcurrentCacheMap( int ticks, int tickDuration, int timeout, TimeUnit unit, int maxCapacity ) {
        super(maxCapacity);
        TICKS = ticks;
        TICK_DURATION = tickDuration;
        TIME_UNIT = unit;
        TIMEOUT = timeout;
        MAX_CAPACITY = maxCapacity;
        this.setAliveTime(timeout, unit);
        buildWheel();
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public V put( K key, V val ) {
        if (wheel != null) {
            TimeoutTask task = new TimeoutTask(key);
            wheel.newTimeout(task, TIMEOUT, TIME_UNIT);
            keyQueue.addLast(key);
        }

        mapSize.incrementAndGet();

        return super.put(key, val);
    }

    public void stop() {
        wheel.stop();
    }

    @Override
    public V remove( Object key ) {
        mapSize.decrementAndGet();
        return super.remove(key);
    }

    @Override
    public void clear() {
        mapSize.set(0);
        super.clear();
    }

    @Override
    public V putIfAbsent( K key, V value ) {
        V result = super.putIfAbsent(key, value);
        if (result == null)
            mapSize.incrementAndGet();

        return result;
    }

    @Override
    public boolean containsKey( Object key ) {
        return super.containsKey(key);
    }

    public long count() {
        return mapSize.get();
    }

    public void setAliveTime( int aliveTime, TimeUnit unit ) {
        this.aliveTime = unit.toMillis(aliveTime);
    }

    private void buildWheel() {
        wheel = new HashedWheelTimer(TICK_DURATION, TIME_UNIT, TICKS);
        wheel.start();

    }

    private V removeTimeout( Object key ) {
        return this.remove(key);
    }

    private class TimeoutTask implements TimerTask {

        private final Object key;
        private long         gmtCreate;

        public TimeoutTask( Object key ) {
            this.key = key;
            gmtCreate = System.currentTimeMillis();
        }

        @Override
        public void run( Timeout timeout ) throws Exception {

            if (System.currentTimeMillis() - gmtCreate < aliveTime) {
                if (containsKey(this.key))
                    wheel.newTimeout(timeout.task(), TIMEOUT, TIME_UNIT);
            }
            else {

                while (keyQueue.contains(key)) {
                    Object rmKey = keyQueue.removeFirst();
                    V val = removeTimeout(rmKey);
                    if (log.isTraceEnabled())
                        log.trace("Map中的数据项超时，从Map中删除，数据内容：{}-{}", key, JSON.toJSONString(val));
                }

            }
        }

    }

}
