package com.duan.springclouddemo.ui.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 2018/9/26.
 *
 * @author DuanJiaNing
 */
@Configuration
public class AppConfig {

//    https://www.cnblogs.com/EasonJim/p/7546136.html
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
