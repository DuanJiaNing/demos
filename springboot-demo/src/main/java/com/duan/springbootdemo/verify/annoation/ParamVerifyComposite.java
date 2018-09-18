package com.duan.springbootdemo.verify.annoation;

import com.duan.springbootdemo.verify.annoation.method.RequestParamValueVerify;
import com.duan.springbootdemo.verify.annoation.method.RequestParamVerify;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/9/14.
 *
 * @author DuanJiaNing
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamVerifyComposite {

    // 二选一灵活校验

    RequestParamValueVerify valueVerify() default @RequestParamValueVerify(param = "", value = "");

    RequestParamVerify value() default @RequestParamVerify(param = "");

}
