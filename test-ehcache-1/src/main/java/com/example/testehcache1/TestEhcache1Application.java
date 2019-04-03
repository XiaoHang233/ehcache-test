package com.example.testehcache1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestEhcache1Application {

    public static void main(String[] args) {
        SpringApplication.run(TestEhcache1Application.class, args);
    }

}
