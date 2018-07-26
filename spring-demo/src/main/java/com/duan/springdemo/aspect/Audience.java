package com.duan.springdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/24.
 *
 * @author DuanJiaNing
 */
@Aspect // 切面
@Component
public class Audience {

    // 切点
    @Pointcut("execution(* com.duan.springdemo.aspect.Performance.performance(..)))")
    public void performance() {
    }

    //    @Before /*通知何时开始执行*/("performance()")
    public void silenceCellPhones() {
        // 通知要做的事情
        System.out.println("Audience.silenceCellPhones");
    }

    //    @Before("performance()")
    public void takeSeats() {
        System.out.println("Audience.takeSeats");
    }

    //    @AfterReturning("performance() && within(com.duan.springdemo.aspect.*)")
    public void applause() { // 鼓掌
        System.out.println("Audience.applause");
    }

    @AfterThrowing("performance() && bean(aspectJInvoker)")
    public void demandRefund() { // 退款
        System.out.println("Audience.demandRefund");
    }

    @Around("performance()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        silenceCellPhones();
        takeSeats();

        joinPoint.proceed();

        applause();
    }

}
