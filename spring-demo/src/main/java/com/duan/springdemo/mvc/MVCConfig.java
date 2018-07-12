package com.duan.springdemo.mvc;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2018/7/6.
 * 简叔 https://www.jianshu.com/p/52f39b799fbb
 *
 * @author DuanJiaNing
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        /*
         * 1.ServletMappings 设置的是 "/" 2.setUseSuffixPatternMatch默认设置为true,
         * 那么,"/user" 就会匹配 "/user.*",也就是说,"/user.html" 的请求会被 "/user" 的 Controller所拦截.
         * 3.如果该值为false,则不匹配
         */
        configurer.setUseSuffixPatternMatch(false);

        /*
         * setUseTrailingSlashMatch的默认值为true
         * 也就是说, "/user" 和 "/user/" 都会匹配到 "/user"的Controller
         */
        configurer.setUseTrailingSlashMatch(true);

    }

    @Bean
    public EnumConverterFactory enumConverterFactory() {
        return new EnumConverterFactory();
    }

    /**
     * 注册自定义的Formatter和Convert,例如, 对于日期类型,枚举类型的转化.
     * 不过对于日期类型,使用更多的是使用
     *
     * @DateTimeFormat(pattern = "yyyy-MM-dd")
     * private Date createTime;
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(enumConverterFactory());
    }

    @Bean
    PlatformSessionArgumentResolvers platformSessionArgumentResolvers() {
        return new PlatformSessionArgumentResolvers();
    }

    /**
     * 参数解析
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(platformSessionArgumentResolvers());
    }

    /**
     * 添加视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 对 "/hello" 的 请求 redirect 到 "/home"
        registry.addRedirectViewController("/hello", "/home");
        // 对 "/admin/**" 的请求 返回 404 的 http 状态
        registry.addStatusController("/admin/**", HttpStatus.NOT_FOUND);
        // 将 "/home" 的 请求响应为返回 "home" 的视图
        registry.addViewController("/home").setViewName("home");
    }

    /**
     * 静态资源处理器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 通过 "/home.html" 请求, 来访问 /resource/static/home.html 静态资源
        registry.addResourceHandler("/home.html").addResourceLocations("classpath:/static/home.html");
    }

    /**
     * SpringMVC支持绑定枚举值参数。
     * 匹配规则 :
     * 字符串则尝试根据Enum#name()转换。
     * 如果找不到匹配的则返回null
     */
    public class EnumConverterFactory implements ConverterFactory<String, Enum> {

        @Override
        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
            return new String2EnumConverter(targetType);
        }

        class String2EnumConverter<T extends Enum<T>> implements Converter<String, T> {

            private Class<T> enumType;

            private String2EnumConverter(Class<T> enumType) {
                this.enumType = enumType;
            }

            @Override
            public T convert(String source) {
                if (source != null && !source.isEmpty()) {
                    try {
                        return Enum.valueOf(enumType, source);
                    } catch (Exception e) {
                    }
                }
                return null;
            }
        }

    }

    @Data
    public class PlatformSession<T> {
        private String username;
        private T id;
        private int expireTime;
    }

    public class PlatformSessionArgumentResolvers implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            return PlatformSession.class.equals(methodParameter.getClass());
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
            HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//            return PlatformSessionUtil.getSession(request);
            return request;
        }
    }

}
