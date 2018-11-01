package com.duan.springbootdemo1.controller;

import com.duan.springbootdemo1.util.DeviceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/11/1.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/deviceType")
    public String testDeviceType() {
        return DeviceUtils.getDeviceType();
    }


}
