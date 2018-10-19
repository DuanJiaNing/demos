package com.duan.springmvcdemo.app.mvc;

import org.springframework.web.servlet.HandlerExecutionChain;

/**
 * Created on 2018/10/19.
 *
 * @author DuanJiaNing
 */
public class SpecHandlerExecutionChain extends HandlerExecutionChain {

    public SpecHandlerExecutionChain(Object handler) {
        super(handler);
    }

    // 6 DispatcherServlet#1087
    // void triggerAfterCompletion(..)

}
