package com.duan.springbootdemo.controller;

import com.duan.springbootdemo.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/6/7.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OrgService orgService;

    @GetMapping("/tr")
    public void testTr() {
        orgService.test();
    }

}
