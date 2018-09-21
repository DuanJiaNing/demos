package com.duan.springclouddemo.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@EnableEurekaServer // 开启 EurekaServer，充当微服务注册中心
public class DiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

}
