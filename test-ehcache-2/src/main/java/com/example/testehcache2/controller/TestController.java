package com.example.testehcache2.controller;

import com.example.testehcache2.entity.TestCache;
import com.example.testehcache2.service.TestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zenghang on 2019/3/28.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestCacheService testCacheService;

    /**
     * 保存数据
     *
     * @param testCache
     * @return
     */
    @GetMapping("/save")
    public String save(TestCache testCache) {
        testCacheService.save(testCache);
        return "save ok";
    }

    /**
     * 取到所有数据
     *
     * @return
     */
    @GetMapping("/all")
    public List<TestCache> all() {
        return testCacheService.findAll();
    }

    /**
     * 删除所有数据
     *
     * @return
     */
    @GetMapping("/delete/all")
    public String deleteAll() {
        testCacheService.deleteAll();
        return "delete all ok";
    }

    /**
     * 根据ID删除数据
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public String delete(Long id) {
        testCacheService.delete(id);
        return "delete " + id + " ok";
    }

    /**
     * 根据key清除指定缓存
     *
     * @param key
     * @return
     */
    @GetMapping("/clear")
    public String clear(Long key) {
        testCacheService.cacheEvict(key);
        return "clear " + key + "ok";
    }

    /**
     * 清除所有缓存
     *
     * @return
     */
    @GetMapping("/clear/all")
    public String clearAll() {
        testCacheService.cacheEvictALl();
        return "clear all ok";
    }

    /**
     * 根据ID取到数据，并存入缓存
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public TestCache get(Long id) {
        return testCacheService.findById(id);
    }

}
