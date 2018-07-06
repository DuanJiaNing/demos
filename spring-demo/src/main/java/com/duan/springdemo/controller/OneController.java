package com.duan.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2018/7/6.
 *
 * @author DuanJiaNing
 */
@Controller
public class OneController {

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

}
