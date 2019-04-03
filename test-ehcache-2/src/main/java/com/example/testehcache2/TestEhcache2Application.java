package com.example.testehcache2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestEhcache2Application {

    public static void main(String[] args) {
        SpringApplication.run(TestEhcache2Application.class, args);
    }

}
