package com.duan.springclouddemo.some;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class SomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomeApplication.class, args);
    }

}
