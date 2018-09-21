package com.duan.springclouddemo.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@EnableEurekaClient
public class PersonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonApplication.class, args);
    }
}
