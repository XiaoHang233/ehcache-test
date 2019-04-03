package com.example.testehcache2.service.impl;

import com.example.testehcache2.entity.TestCache;
import com.example.testehcache2.repository.TestCacheRepository;
import com.example.testehcache2.service.TestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zenghang on 2019/3/28.
 */
@Service
public class TestCacheServiceImpl implements TestCacheService {
    @Autowired
    private TestCacheRepository repository;

    @Override
    public TestCache save(TestCache testCache) {
        return repository.save(testCache);
    }

    @Override
    public void update(TestCache testCache) {
        repository.save(testCache);
    }

    @Override
    public List<TestCache> findAll() {
        return repository.findAll();
    }

    @Cacheable(key = "#p0", value = "test")
    @Override
    public TestCache findById(Long id) {
        System.out.println("ID:" + id + "====>通过数据库拿到数据！");
        return repository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @CacheEvict(key = "#p0", value = "test")
    @Override
    public void cacheEvict(Long id) {
        System.out.println("缓存ID:" + id + "====》被清除！");
    }

    @CacheEvict(value = "test", allEntries = true)
    @Override
    public void cacheEvictALl() {
        System.out.println("所有缓存被清除！");
    }
}
