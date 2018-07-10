package com.duan.springdemo.config;

import org.springframework.context.annotation.*;

import java.util.Date;

/**
 * Created on 2018/7/3.
 *
 * @author DuanJiaNing
 */
@Configuration
@Import({
        CDPlayerConfig.class,
        MVCConfig.class})
@ComponentScan("com.duan.springdemo")
@ImportResource({"classpath:spring-config.xml", "classpath:root-config.xml"})
public class AppConfig {

    @Bean
    @Profile("prod")
    public Date nowDate() {
        return new Date();
    }


}
