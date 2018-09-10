package com.duan.springbootcommondemo.controller;

import com.duan.springbootcommondemo.config.AppInfo;
import com.duan.springbootcommondemo.rest.ResultModel;
import com.duan.springbootcommondemo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/9/10.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/info")
    public ResultModel<AppInfo> getAppInfo() {
        return new ResultModel<>(appService.getAppInfo());
    }

}
