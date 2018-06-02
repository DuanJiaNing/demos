package com.duan.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan({"com.duan.springbootdemo"})
public class SampleController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private Config config;

    @RequestMapping("/")
    @ResponseBody
    public String main() {
        return "Hello world "+port +"\n"+config;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

}
