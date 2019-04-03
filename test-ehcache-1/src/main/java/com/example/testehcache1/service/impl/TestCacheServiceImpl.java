package com.example.testehcache1.service.impl;

import com.example.testehcache1.entity.TestCache;
import com.example.testehcache1.repository.TestCacheRepository;
import com.example.testehcache1.service.TestCacheService;
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

    @Cacheable(key = "methodName", value = "testList")
    @Override
    public List<TestCache> findAll() {
        return repository.findAll();
    }

    @Cacheable(key = "methodName", value = "testList")
    @Override
    public String findAll11() {
        System.out.println("11111");
        return "11111";
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
        System.out.println("test所有缓存被清除！");
    }

    @CacheEvict(value = "testList", allEntries = true)
    @Override
    public void testListAllClear() {
        System.out.println("testList所有缓存被清除！");
    }
}
