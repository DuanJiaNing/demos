package com.duan.springbootcommondemo.controller;

import com.duan.common.base.restful.ResultModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping
public class LoginController {

    @PostMapping("/doLogin")
    public ResultModel doLogin() {
        return null;
    }


}
