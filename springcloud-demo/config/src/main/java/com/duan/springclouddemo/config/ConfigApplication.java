package com.duan.springclouddemo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@EnableConfigServer // 开启配置服务支持
@EnableEurekaClient // 作为 eureka 客户端
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

}
