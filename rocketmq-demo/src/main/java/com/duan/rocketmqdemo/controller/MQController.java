package com.duan.rocketmqdemo.controller;

import com.duan.rocketmqdemo.TestBean;
import com.duan.rocketmqdemo.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/4/28.
 *
 * @author 段佳宁
 */
@RequestMapping
@RestController
public class MQController {

    @Autowired
    private TestBean bean;

    @RequestMapping("/{times}")
    public void send(@PathVariable Integer times,
                     @RequestParam(value = "obj", required = false) Object obj) {

        if (obj == null) obj = "test msg";

        for (int i = 0; i < times; i++) {
            Util.p.accept(obj+" "+i);
            bean.send(obj);
        }

    }

}
