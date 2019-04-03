package com.example.testehcache1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zenghang on 2019/3/28.
 */
@Controller
public class IndexController {
    @RequestMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }
}
