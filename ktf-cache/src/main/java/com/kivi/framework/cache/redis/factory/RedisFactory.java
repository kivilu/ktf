package com.kivi.framework.cache.redis.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

import com.kivi.framework.cache.factory.BaseCacheFactory;

/**
 * Redis缓存工厂
 */
public class RedisFactory extends BaseCacheFactory {

    private final KtfRedisCacheManager cacheManager;
    private static volatile Object     locker = new Object();
    private static final Logger        log    = LoggerFactory.getLogger(RedisFactory.class);

    public RedisFactory( KtfRedisCacheManager cacheManager ) {
        this.cacheManager = cacheManager;

    }

    /*
     * private ValueOperations<String, Object> getOrAddCache() { //CacheManager
     * cacheManager = getCacheManager(); ValueOperations<String, Object> ops =
     * redisTemplate.opsForValue(); return ops; }
     */

    private Cache getOrAddCache( String cacheName, Long expire ) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            synchronized (locker) {
                cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    log.warn("无法找到缓存 [" + cacheName + "]的配置, 使用默认配置.");
                    cache = cacheManager.createCache(cacheName);

                    Map<String, Long> expires = new HashMap<>();
                    expires.put(cacheName, expire);
                    cacheManager.setExpires(expires);
                    log.debug("缓存 [" + cacheName + "] 启动.");
                }
            }
        }
        return cache;
    }

    private Cache getOrAddCache( String cacheName ) {
        return this.getOrAddCache(cacheName, 3600000L);
    }

    @Override
    public void put( String cacheName, Object key, Object value ) {
        getOrAddCache(cacheName).put(key, value);
    }

    @Override
    public void put( String cacheName, Object key, Object value, Long expire ) {
        getOrAddCache(cacheName, expire).put(key, value);
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public <T> T get( String cacheName, Object key ) {
        ValueWrapper element = getOrAddCache(cacheName).get(key);
        return element != null ? (T) element.get() : null;
    }

    @Override
    public void remove( String cacheName, Object key ) {
        getOrAddCache(cacheName).evict(key);
    }

    @Override
    public void removeAll( String cacheName ) {
        getOrAddCache(cacheName).clear();
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public Set<String> getKeys( String cacheName ) {
        return cacheManager.getRedisOperations().keys(cacheName);
    }

}
