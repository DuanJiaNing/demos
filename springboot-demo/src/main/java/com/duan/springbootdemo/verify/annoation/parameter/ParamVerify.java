package com.duan.springbootdemo.verify.annoation.parameter;

import com.duan.springbootdemo.verify.VerifyRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/9/14.
 * 限定 controller 处理器方法具体参数的校验规则
 *
 * @author DuanJiaNing
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
public @interface ParamVerify {

    /**
     * 校验规则
     */
    VerifyRule rule() default VerifyRule.NOT_NULL;

}
