package com.duan.springbootdemo.verify.annoation.parameter;

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
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface ParamValueVerify {

    /**
     * 校验规则
     */
    VerifyValueRule rule() default VerifyValueRule.EQUAL;

    /**
     * 校验值
     */
    String value();

}
