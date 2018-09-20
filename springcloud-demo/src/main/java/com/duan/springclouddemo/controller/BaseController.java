package com.duan.springclouddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018/9/20.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/")
public class BaseController {

    @GetMapping
    @ResponseBody
    public String test() {
        return "Hello Spring Boot, Docker and CloudComb!";
    }

}
