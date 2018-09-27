package com.duan.springbootcommondemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("/index");
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/login").setViewName("/login");
        log.info("WebMvcConfig#addViewControllers");
    }
}
