package com.example.basecache.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;

/**
 * Created by zenghang on 2019/3/29.
 */
public class CacheConfigXML {
    //配置参数
    private String xmlConfigUrl = "/ehcache.xml";

    //持久化配置
    private Configuration xmlConfig = null;
    private CacheManager cacheManager = null;
    private Cache cache = null;

    public CacheConfigXML setXmlConfigUrl(String xmlConfigUrl) {
        this.xmlConfigUrl = xmlConfigUrl;
        return this;
    }

    public void init() {
        URL myUrl = CacheConfigXML.class.getResource(xmlConfigUrl);
        xmlConfig = new XmlConfiguration(myUrl);
        xmlConfig.getCacheConfigurations().get("basicCache").getValueType();
        xmlConfig.getCacheConfigurations().get("basicCache").getKeyType();
        cacheManager = newCacheManager(xmlConfig);
        cacheManager.init();
    }

    public void setCache(String cacheName) {
        cache = cacheManager.getCache(cacheName,
                xmlConfig.getCacheConfigurations().get(cacheName).getKeyType(),
                xmlConfig.getCacheConfigurations().get(cacheName).getValueType());
    }
}
