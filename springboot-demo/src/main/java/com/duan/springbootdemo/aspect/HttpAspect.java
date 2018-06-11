package com.duan.springbootdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/6/8.
 *
 * @author DuanJiaNing
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.duan.springbootdemo.controller.OrgController.*(..))")
    private void pointcut() {

    }

    @Before("pointcut()")
    public void logBefore(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("\nurl={}" +
                        "\nmethod={}" +
                        "\nip={}" +
                        "\nclass_method={}",
                request.getRequestURL(),
                request.getMethod(),
                request.getRemoteAddr(),
                point.getSignature());

    }

    @Before("pointcut()")
    public void logAfter() {
        log.info("log after");
    }

    @AfterReturning(pointcut = "pointcut()",returning = "returnObj")
    public void afterReturn(Object returnObj) {
        log.error(returnObj.toString());
    }

}
