package com.example.testehcache2.config.ehcache;

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

    @NonNull
    private org.ehcache.core.EhcacheManager ehcacheManager;
    private String xmlUrl = "/ehcache/ehcache.xml";

    public EhcacheManager() {
    }

    public EhcacheManager(org.ehcache.core.EhcacheManager ehcacheManager) {
        this.ehcacheManager = ehcacheManager;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        URL myUrl = EhcacheManager.class.getResource(xmlUrl);
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager cacheManager = newCacheManager(xmlConfig);
        cacheManager.init();

//        CacheManager cacheManager = getCacheManager();
        Assert.state(cacheManager != null, "No CacheManager set");

        Status status = cacheManager.getStatus();
        if (!Status.AVAILABLE.equals(status)) {
            throw new IllegalStateException(
                    "An 'alive' EhCache CacheManager is required - current cache is " + status.toString());
        }


        Map<String, CacheConfiguration<?, ?>> cacheConfigurations = xmlConfig.getCacheConfigurations();

        Collection<Cache> caches = new LinkedHashSet<>(cacheConfigurations.size());

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
