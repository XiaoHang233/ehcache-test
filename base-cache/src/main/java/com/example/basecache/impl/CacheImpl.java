package com.example.basecache.impl;

import org.ehcache.core.EhcacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zenghang on 2019/3/29.
 */
@Component
public class CacheImpl<K, V> {
    @Autowired
    private EhcacheManager manager;

    /**
     * 存入缓存
     *
     * @param key
     * @param val
     */
    public void put(K key, V val) {
    }

    /**
     * 根据key清除缓存
     *
     * @param key
     */
    public void clear(K key) {
    }

    /**
     * 清除所有缓存
     */
    public void clearAll() {
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public Object get(K key) {
        return null;
    }

    /**
     * 通过key判断缓存是否存在
     *
     * @param key
     * @return
     */
    public boolean isExist(K key) {
        return false;
    }
}
