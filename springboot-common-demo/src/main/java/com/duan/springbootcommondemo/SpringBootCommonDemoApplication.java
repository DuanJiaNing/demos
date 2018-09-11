package com.duan.springbootcommondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created on 2018/7/5.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@ImportResource("classpath:config/spring/spring-*.xml")
//@EnableConfigurationProperties
public class SpringBootCommonDemoApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpringBootCommonDemoApplication.class);
        app.run(args);
    }

}
