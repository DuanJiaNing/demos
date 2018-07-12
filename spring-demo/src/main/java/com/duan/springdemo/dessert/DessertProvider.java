package com.duan.springdemo.dessert;

import com.duan.springdemo.dessert.annotation.Creamy;
import com.duan.springdemo.dessert.annotation.Multiple;
import com.duan.springdemo.dessert.annotation.Soft;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/11.
 *
 * @author DuanJiaNing
 */
@Component
@Slf4j
public class DessertProvider implements BeanNameAware {

    @Autowired
    @Soft
    private Dessert cake;

    @Autowired
    @Creamy
    private Dessert iceCream;

    @Autowired
    @Multiple
    private Dessert cookies;

    @Autowired
    @Soft
    @Multiple
    private Dessert alsoCookies;


    @Override
    public void setBeanName(String name) {

        log.info(cake.getClass().toString()); // com.duan.springdemo.dessert.Cake
        log.info(iceCream.getClass().toString()); // com.duan.springdemo.dessert.IceCream
        log.info(cookies.getClass().toString()); // com.duan.springdemo.dessert.Cookies
        log.info(alsoCookies.getClass().toString()); // com.duan.springdemo.dessert.Cookies

    }
}
