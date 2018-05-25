package com.duan.springbootdemo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018/4/24.
 *
 * @author 段佳宁
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    public String main() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

}
