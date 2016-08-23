package com.rk.example.sqlsafe.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hi!, you need to try out this url : /api/data/getById?id=123";
    }

}