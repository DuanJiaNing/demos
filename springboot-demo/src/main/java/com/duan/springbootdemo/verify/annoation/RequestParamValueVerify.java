package com.duan.springbootdemo.verify.annoation;

import com.duan.springbootdemo.verify.VerifyValueRule;

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
public @interface RequestParamValueVerify {

    /**
     * 需要进行校验的参数名
     */
    String param() default "";

    /**
     * 校验规则
     */
    VerifyValueRule rule() default VerifyValueRule.NON;

    /**
     * 校验值
     */
    String value();

}
