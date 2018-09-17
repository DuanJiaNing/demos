package com.duan.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created on 2018/9/14.
 *
 * @author DuanJiaNing
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * 跨域设置
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");

        return corsConfiguration;
    }

    /**
     * 跨域过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
//
//    @Bean
//    public HttpMessageConverter stringHttpMessageConverter() {
//        return new StringHttpMessageConverter();
//    }
//
//    @Bean
//    public HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        return new MappingJackson2HttpMessageConverter();
//    }
//
//    @Bean
//    public FastJsonConfig fastJsonConfig() {
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        SerializerFeature writeMapNullValue = SerializerFeature.WriteMapNullValue;
//        SerializerFeature WriteNullStringAsEmpty = SerializerFeature.WriteNullStringAsEmpty;
//        SerializerFeature WriteNullNumberAsZero = SerializerFeature.WriteNullNumberAsZero;
//        SerializerFeature WriteNullListAsEmpty = SerializerFeature.WriteNullListAsEmpty;
//        fastJsonConfig.setSerializerFeatures(writeMapNullValue, WriteNullStringAsEmpty,
//                WriteNullNumberAsZero, WriteNullListAsEmpty);
//        return fastJsonConfig;
//    }
//
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters(
//            @Qualifier("fastJsonConfig") FastJsonConfig fastJsonConfig) {
//        FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        HttpMessageConverter<?> converter = fastConverter;
//        return new HttpMessageConverters(converter);
//    }

}
