package com.duan.springdemo.aspect;

import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/24.
 *
 * @author DuanJiaNing
 */
@Component
public class PerformanceImpl implements Performance {

    @Override
    public void performance() {
        System.out.println("PerformanceImpl.performance");
    }
}
