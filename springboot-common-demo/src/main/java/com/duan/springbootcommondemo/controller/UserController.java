package com.duan.springbootcommondemo.controller;

import com.duan.common.base.restful.ResultModel;
import com.duan.springbootcommondemo.entity.User;
import com.duan.springbootcommondemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResultModel<Page<User>> userList() {
        return new ResultModel<>(userService.findList(1, 10));
    }


}
