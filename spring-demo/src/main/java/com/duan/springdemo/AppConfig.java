package com.duan.springdemo;

import org.springframework.context.annotation.*;

import java.util.Date;

/**
 * Created on 2018/7/3.
 *
 * @author DuanJiaNing
 */
@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {

    @Bean
    @Profile("prod")
    public Date nowDate() {
        return new Date();
    }


}
