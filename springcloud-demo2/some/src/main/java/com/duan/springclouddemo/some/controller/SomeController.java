package com.duan.springclouddemo.some.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@RestController
public class SomeController {

    @Value("${my.some}")
    private String some;

    @GetMapping("/getSome")
    public String some() {
        return some + Math.random();
    }

}
