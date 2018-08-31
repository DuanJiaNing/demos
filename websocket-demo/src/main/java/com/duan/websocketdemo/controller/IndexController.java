package com.duan.websocketdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/8/31.
 *
 * @author DuanJiaNing
 */

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/{uid}")
    public String index(@PathVariable Long uid, HttpServletRequest request) {
        request.getSession().setAttribute("uid", uid); // uid 保存到会话，用于 websocket 连接时唯一标记会话

        return "index";
    }

}
