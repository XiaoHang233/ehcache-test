package com.example.basecache.config;

import sun.security.util.Cache;

/**
 * Created by zenghang on 2019/3/29.
 */
public interface CacheConfigService {
    Cache init();

    void close();
}
