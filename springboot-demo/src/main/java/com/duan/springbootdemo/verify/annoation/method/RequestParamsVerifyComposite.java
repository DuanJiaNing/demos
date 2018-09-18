package com.duan.springbootdemo.verify.annoation.method;

import com.duan.springbootdemo.verify.annoation.ParamVerifyComposite;

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
@Target(value = ElementType.METHOD)
public @interface RequestParamsVerifyComposite {

    /**
     * 需要校验的参数、比对的值与规则映射
     */
    ParamVerifyComposite[] value();

}
