package com.duan.springmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created on 2018/8/15.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

    @RequestMapping(method = GET)
    public String home() {
        return "home";
    }

}
