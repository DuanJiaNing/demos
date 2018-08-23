package com.duan.springmvcdemo.app;

import org.springframework.context.annotation.*;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created on 2018/8/14.
 *
 * @author DuanJiaNing
 */
@Configuration // 定义 ContextLoaderListener 创建的应用上下文中的 bean
@ComponentScan(basePackages = {"com.duan.springmvcdemo"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@ImportResource(locations = {"classpath: spring/spring-*.xml"})
public class RootConfig {

    @Bean // 使用 MultipartFile 接收时才需要该 bean，使用 javax.servlet.http.Part 时无需该 bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
