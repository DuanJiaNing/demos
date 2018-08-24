package com.duan.springmvcdemo.spittle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created on 2018/8/15.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

    @RequestMapping
    public String home() {
        return "home";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

}
