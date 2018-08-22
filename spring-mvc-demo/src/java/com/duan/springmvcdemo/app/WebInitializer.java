package com.duan.springmvcdemo.app;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Created on 2018/8/14.
 * web.xml 中配置 DispatcherServlet 的替代方案
 *
 * @author DuanJiaNing
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override // 注册 DispathcerServlet 之后回调
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        // 文件上传 依赖 multipartResolver bean
        registration.setMultipartConfig(new MultipartConfigElement("C:/Users/ai/Desktop/"));
        registration.setLoadOnStartup(1);
        registration.setInitParameter("a", "A");
    }


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
