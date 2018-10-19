package com.duan.springmvcdemo.app.mvc;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/10/19.
 *
 * @author DuanJiaNing
 */
public class SpecHandlerAdapter implements HandlerAdapter {

    // 2 DispatcherServlet#1228
    // getHandlerAdapter -> this.handlerAdapters#supports
    public boolean supports(Object handler) {
        return handler instanceof SpecHandler;
    }

    // 3 DispatcherServlet#991
    // doDispatch -> handle
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SpecHandler hand = (SpecHandler) handler;
        ModelAndView mv = new ModelAndView();
        mv.setViewName(SpecHandler.view_name);

        return mv;
    }

    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }

}
