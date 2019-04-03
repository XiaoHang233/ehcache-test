package com.example.testehcache1.config.ehcache;

import net.sf.ehcache.Element;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * Created by zenghang on 2100019/4/1.
 */
public class EhcacheImpl implements org.springframework.cache.Cache {
    private org.ehcache.Cache ehcache;
    private String name;

    public EhcacheImpl(org.ehcache.Cache ehcache, String name) {
        this.ehcache = ehcache;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.ehcache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Element element = lookup(key);
        return toValueWrapper(element);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = this.ehcache.get(key);
        if (value != null && type != null && !type.isInstance(value)) {
            throw new IllegalStateException(
                    "Cached value is not of required type [" + type.getName() + "]: " + value);
        }
        return (T) value;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T get(Object key, Callable<T> valueLoader) {
        Element element = lookup(key);
        if (element != null) {
            return (T) element.getObjectValue();
        } else {
            return loadValue(key, valueLoader);
        }
    }

    @Override
    public void put(Object key, Object value) {
        this.ehcache.put(key, value);
    }

    @Nullable
    private Element lookup(Object key) {
        Object value = this.ehcache.get(key);
        if (value == null) {
            return null;
        } else {
            return new Element(key, value);
        }
    }

    private <T> T loadValue(Object key, Callable<T> valueLoader) {
        T value;
        try {
            value = valueLoader.call();
        } catch (Throwable ex) {
            throw new ValueRetrievalException(key, valueLoader, ex);
        }
        put(key, value);
        return value;
    }

    @Nullable
    private ValueWrapper toValueWrapper(@Nullable Element element) {
        return (element != null ? new SimpleValueWrapper(element.getObjectValue()) : null);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        Object o = this.ehcache.putIfAbsent(key, value);
        return toValueWrapper(new Element(key, o));

//        Object existingValue = this.get(key);
//        if (existingValue == null) {
//            this.put(key, value);
//            return null;
//        } else {
//            return () -> existingValue;
//        }
    }

    @Override
    public void evict(Object key) {
        this.ehcache.remove(key);
    }

    @Override
    public void clear() {
        this.ehcache.clear();
    }
}
