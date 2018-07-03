package com.kivi.framework.web.shiro.cache;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kivi.framework.cache.constant.KtfCache;

public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private Logger           logger    = LoggerFactory.getLogger(this.getClass());

    /**
     * The Redis key prefix for the sessions
     */
    private static String    keyPrefix = KtfCache.SHIRO_KEY_PREFIX;

    /**
     * The wrapped instance.
     */
    private final RedisCache cache;

    /**
     * 通过一个JedisManager实例构造RedisCache
     */
    public ShiroRedisCache( RedisCache cache ) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cache = cache;
    }

    /**
     * Constructs a cache instance with the specified Redis manager and using a
     * custom key prefix.
     * 
     * @param cache
     *            The cache manager instance
     * @param prefix
     *            The Redis key prefix
     */
    public ShiroRedisCache( RedisCache cache, String prefix ) {

        this(cache);

        // set the prefix
        keyPrefix = prefix;
    }

    /**
     * Returns the Redis session keys prefix.
     * 
     * @return The prefix
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Sets the Redis sessions key prefix.
     * 
     * @param keyPrefix
     *            The prefix
     */
    public void setKeyPrefix( String keyPrefix ) {
        keyPrefix = keyPrefix;
    }

    /**
     * 获得byte[]型的key
     * 
     * @param key
     * @return
     */
    private byte[] getByteKey( K key ) {
        String preKey = keyPrefix + key;
        return preKey.getBytes();
    }

    /**
     * 获得byte[]型的key
     * 
     * @param key
     * @return
     */
    private String getKey( K key ) {
        String preKey = keyPrefix + key;
        return preKey;
    }

    @Override
    public V get( K key ) throws CacheException {
        logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if (key == null) {
                return null;
            }
            else {
                String rawValue = cache.get(getKey(key), String.class);
                Type type = new TypeReference<V>() {
                }.getType();
                V value = (V) JSON.parseObject(rawValue, type, JSON.DEFAULT_PARSER_FEATURE);
                return value;
            }
        }
        catch (Throwable t) {
            throw new CacheException(t);
        }

    }

    @Override
    public V put( K key, V value ) throws CacheException {
        logger.debug("根据key从存储 key [" + key + "]");
        try {
            String sValue = JSON.toJSONString(value, SerializerFeature.WriteClassName);
            cache.put(getKey(key), sValue);
            return value;
        }
        catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove( K key ) throws CacheException {
        logger.debug("从redis中删除 key [" + key + "]");
        try {
            V previous = get(key);
            cache.evict(getKey(key));
            return previous;
        }
        catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("从redis中删除所有元素");
        try {
            cache.clear();
        }
        catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Set<K> keys() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        return null;
    }

}
