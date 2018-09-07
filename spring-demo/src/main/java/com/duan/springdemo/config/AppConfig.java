package com.duan.springdemo.config;

import com.duan.springdemo.dessert.Dessert;
import com.duan.springdemo.dessert.IceCream;
import com.duan.springdemo.envPro.EnvConfig;
import com.duan.springdemo.mvc.MVCConfig;
import com.duan.springdemo.mvc.shop.ShoppingConfig;
import com.duan.springdemo.player.CDPlayerConfig;
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
        MVCConfig.class,
        ShoppingConfig.class,
        EnvConfig.class,
        AspectConfig.class})
@ComponentScan("com.duan.springdemo")
@ImportResource({"classpath:spring-*.xml", "classpath:root-config.xml"})
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
//    @Profile("prod")
    public Date nowDate() {
        return new Date();
    }

    @Bean
    /*@Primary*/
    public Dessert myDessert() {
        return new IceCream();
    }

}
