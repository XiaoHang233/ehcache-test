package com.example.basecache.config;

import com.example.basecache.model.CacheBean;
import org.ehcache.Cache;
import org.ehcache.CacheManager;

import java.net.URI;

import static java.net.URI.create;
import static org.ehcache.clustered.client.config.builders.ClusteredResourcePoolBuilder.clusteredDedicated;
import static org.ehcache.clustered.client.config.builders.ClusteringServiceConfigurationBuilder.cluster;
import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.heap;
import static org.ehcache.config.units.MemoryUnit.MB;
import static org.slf4j.LoggerFactory.getLogger;

public class CacheConfigProgrammatic<K, V extends CacheBean> {
    private String url = "terracotta://localhost:9410/clustered";
    private String serverResource = "main";
    private String cacheName = "basicCache";
    private Integer heap = 100;
    private Integer offheap = 1;
    private Integer clusteredDedicated = 5;
    private K key;
    private V value;

    public Cache<K, V> init() {
        URI uri = create(url);
        try (CacheManager cacheManager = newCacheManagerBuilder()
                .with(cluster(uri).autoCreate().defaultServerResource(serverResource))
                .withCache(cacheName,
                        newCacheConfigurationBuilder(key.getClass(), value.getClass(),
                                heap(heap).offheap(offheap, MB).with(clusteredDedicated(clusteredDedicated, MB))))
                .build(true)) {

            return (Cache<K, V>) cacheManager.getCache(cacheName, key.getClass(), value.getClass());
        }
    }

    public CacheConfigProgrammatic<K, V> setUrl(String url) {
        this.url = url;
        return this;
    }

    public CacheConfigProgrammatic<K, V> setServerResource(String serverResource) {
        this.serverResource = serverResource;
        return this;
    }

    public CacheConfigProgrammatic<K, V> setCacheName(String cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    public CacheConfigProgrammatic<K, V> setHeap(Integer heap) {
        this.heap = heap;
        return this;
    }

    public CacheConfigProgrammatic<K, V> setOffheap(Integer offheap) {
        this.offheap = offheap;
        return this;
    }

    public CacheConfigProgrammatic<K, V> setClusteredDedicated(Integer clusteredDedicated) {
        this.clusteredDedicated = clusteredDedicated;
        return this;
    }

    public CacheConfigProgrammatic<K, V> setKey(K key) {
        this.key = key;
        return this;
    }

    public CacheConfigProgrammatic<K, V> setValue(V value) {
        this.value = value;
        return this;
    }
}
