package com.example.testehcache1.repository;

import com.example.testehcache1.entity.TestCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zenghang on 2019/3/28.
 */
@Repository
public interface TestCacheRepository extends JpaRepository<TestCache, Long> {
}
