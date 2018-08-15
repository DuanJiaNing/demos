package com.duan.springmvcdemo.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created on 2018/8/14.
 *
 * @author DuanJiaNing
 */
@Configuration // 定义 ContextLoaderListener 创建的应用上下文中的 bean
@ComponentScan(basePackages = {"com.duan.springmvcdemo"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
}
