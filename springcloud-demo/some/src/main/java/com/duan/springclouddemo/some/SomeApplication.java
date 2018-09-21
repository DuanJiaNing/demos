package com.duan.springclouddemo.some;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomeApplication.class, args);
    }

}
