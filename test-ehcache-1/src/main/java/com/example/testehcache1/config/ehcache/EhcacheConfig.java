package com.example.testehcache1.config.ehcache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zenghang on 2019/3/28.
 */
@Configuration
@EnableCaching
public class EhcacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhcacheManager();
    }

//    private EhcacheManager ehcacheManager;

//    EhcacheConfig() {
//        URL myUrl = EhcacheConfig.class.getResource("/ehcache.xml");
//        ehcacheManager = (EhcacheManager) newCacheManager(new XmlConfiguration(myUrl));
//        ehcacheManager.init();
//    }

//    @Override
//    public org.springframework.cache.CacheManager cacheManager() {
//        return ehcacheManager;
//    }
}
