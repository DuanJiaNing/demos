package com.duan.springbootcommondemo.config;

import com.duan.common.spring.verify.ServletInvocableHandlerMethodArgumentVerify;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@Configuration
public class WebConfig extends WebMvcRegistrationsAdapter {

    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter() {
            @Override
            protected ServletInvocableHandlerMethod createInvocableHandlerMethod(HandlerMethod handlerMethod) {
                return new ServletInvocableHandlerMethodArgumentVerify(handlerMethod);
            }
        };
    }


}
