package com.duan.springmvcdemo.app.mvc;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/10/19.
 *
 * @author DuanJiaNing
 */
@Component
public class SpecHandlerMapping implements HandlerMapping {

    // 1 DispatcherServlet#967
    // getHandler -> this.handlerMappings#getHandler
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        String uri = request.getRequestURI();
        return new SpecHandlerExecutionChain(new SpecHandler(uri));
    }

}
