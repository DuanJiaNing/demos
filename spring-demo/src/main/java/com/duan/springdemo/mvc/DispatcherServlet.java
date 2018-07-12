package com.duan.springdemo.mvc;

import com.duan.springdemo.config.AppConfig;
import com.duan.springdemo.player.CDPlayerConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created on 2018/7/6.
 *
 * @author DuanJiaNing
 */
public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                AppConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                CDPlayerConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/mvc"};
    }
}
