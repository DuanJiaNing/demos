package com.duan.springmvcdemo.app.mvc;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created on 2018/10/19.
 *
 * @author DuanJiaNing
 */
public class SpecViewResolver implements ViewResolver {

    // 4 DispatcherServlet#1369
    // resolveViewName -> this.viewResolvers
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return viewName.equals(SpecHandler.view_name) ? new SpecView() : null;
    }

}
