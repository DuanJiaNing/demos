package com.duan.springdemo.aspect;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/24.
 *
 * @author DuanJiaNing
 */
@Component
public class Invoker implements BeanNameAware {

    @Autowired
    private Performance performance;

    @Override
    public void setBeanName(String name) {
        performance.performance();
    }

}
