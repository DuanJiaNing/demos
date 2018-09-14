package com.duan.springbootdemo.verify.annoation;

import com.duan.springbootdemo.verify.VerifyRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/9/14.
 *
 * @author DuanJiaNing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParamVerify {

    /**
     * 需要进行校验的参数名
     */
    String param() default "";

    /**
     * 校验规则
     */
    VerifyRule rule();

}
