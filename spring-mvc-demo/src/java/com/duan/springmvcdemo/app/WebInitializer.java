package com.duan.springmvcdemo.app;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created on 2018/8/14.
 * web.xml 中配置 DispatcherServlet 的替代方案
 *
 * @author DuanJiaNing
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 第一个 spring 应用上下文
    // ContextLoaderListener，加载驱动后端的中间层和数据层组件 bean
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    // 第二个 spring 应用上下文
    // DispatcherServlet，加载包含 web 组件的 bane，如控制器、视图解析器以及处理器映射
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
