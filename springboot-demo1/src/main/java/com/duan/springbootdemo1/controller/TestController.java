package com.duan.springbootdemo1.controller;

import com.duan.springbootdemo1.config.annotation.OperatorLog;
import com.duan.springbootdemo1.restful.ResultModel;
import com.duan.springbootdemo1.util.DeviceUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2018/11/1.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/deviceType")
    @OperatorLog("modelCode is {model.code} ,result is {$},arg num={num}")
    public String testDeviceType(@RequestBody ResultModel model, @RequestParam String num) {
        return DeviceUtils.getDeviceType();
    }


}
