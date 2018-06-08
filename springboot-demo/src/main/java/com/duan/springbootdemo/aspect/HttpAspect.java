package com.duan.springbootdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/6/8.
 *
 * @author DuanJiaNing
 */
@Aspect
@Component
public class HttpAspect {

    @Before("execution(public * com.duan.springbootdemo.controller.OrgController.*(..))")
    public void logBefore() {
        System.out.println("log before");
    }

    @Before("execution(public * com.duan.springbootdemo.controller.OrgController.*(..))")
    public void logAfter() {
        System.out.println("log after");
    }

}
