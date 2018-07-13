package com.duan.springdemo.mvc.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created on 2018/7/13.
 *
 * @author DuanJiaNing
 */
@Configuration
public class ShoppingConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.INTERFACES)
    // ScopedProxyMode.INTERFACES 指明注入时使用 JDK 动态代理
    public ShoppingCart shoppingCart() {
        return new ShoppingCartImpl();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    // ScopedProxyMode.TARGET_CLASS 指明注入时使用 CGLib 代理
    public ShoppingCart2 shoppingCart2() {
        return new ShoppingCart2();
    }


}
