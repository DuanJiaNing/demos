package com.duan.springmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created on 2018/8/17.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {


    @RequestMapping("/register")
    public String register() {
        return "register";
    }

}
