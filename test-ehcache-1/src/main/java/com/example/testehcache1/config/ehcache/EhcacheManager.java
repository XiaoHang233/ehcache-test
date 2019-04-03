package com.example.testehcache1.config.ehcache;

import org.ehcache.CacheManager;
import org.ehcache.Status;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;

/**
 * Created by zenghang on 2019/4/1.
 */
public class EhcacheManager extends AbstractTransactionSupportingCacheManager {
    //定义两个全局变量，主要是方便自定义配置
    @NonNull
    private org.ehcache.core.EhcacheManager ehcacheManager;
    private String xmlUrl = "/ehcache/ehcache.xml";

    //这里定义三个构造函数，其实应该是只有两个，当然根据不同的需求可以定义多个，空的就是使用默认配置，一般来说只需要传入xml的url地址就行
    public EhcacheManager() {
    }

    public EhcacheManager(org.ehcache.core.EhcacheManager ehcacheManager) {
        this.ehcacheManager = ehcacheManager;
    }

    public EhcacheManager(org.ehcache.core.EhcacheManager ehcacheManager, String xmlUrl) {
        this.ehcacheManager = ehcacheManager;
        this.xmlUrl = xmlUrl;
    }

    //这个是重点，直接从EhCacheCacheManager复制过来的，避免自己手写，容易出现错误
    @Override
    protected Collection<? extends Cache> loadCaches() {
        URL myUrl = EhcacheManager.class.getResource(xmlUrl);
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        org.ehcache.CacheManager cacheManager = newCacheManager(xmlConfig);
        cacheManager.init();

        Assert.state(cacheManager != null, "No CacheManager set");

        Status status = cacheManager.getStatus();
        if (!Status.AVAILABLE.equals(status)) {
            throw new IllegalStateException(
                    "An 'alive' EhCache CacheManager is required - current cache is " + status.toString());
        }


        Map<String, CacheConfiguration<?, ?>> cacheConfigurations = xmlConfig.getCacheConfigurations();

        Collection<Cache> caches = new LinkedHashSet<>(cacheConfigurations.size());

        //主要是修改这里
        for (String key : cacheConfigurations.keySet()) {
            Class<?> keyType = cacheConfigurations.get(key).getKeyType();
            Class<?> valueType = cacheConfigurations.get(key).getValueType();
            org.ehcache.Cache cache = cacheManager.getCache(key, keyType, valueType);
            caches.add(new EhcacheImpl(cache, key));
        }
        return caches;
    }

    @Nullable
    public CacheManager getCacheManager() {
        return this.ehcacheManager;
    }

}
