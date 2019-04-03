package com.example.testehcache1.service;


import com.example.testehcache1.entity.TestCache;

import java.util.List;

/**
 * Created by zenghang on 2019/3/28.
 */
public interface TestCacheService {
    /**
     * 保存数据
     */
    TestCache save(TestCache testCache);

    /**
     * 更新数据
     */
    void update(TestCache testCache);

    /**
     * 拿到所有数据
     */
    List<TestCache> findAll();

    String findAll11();

    /**
     * 根据ID拿到数据，并加入缓存
     */
    TestCache findById(Long id);

    /**
     * 删除所有数据
     */
    void deleteAll();

    /**
     * 根据ID删除数据
     */
    void delete(Long id);

    /**
     * 根据ID清除指定缓存
     */
    void cacheEvict(Long id);

    /**
     * 清除所有缓存
     */
    void cacheEvictALl();

    void testListAllClear();
}
