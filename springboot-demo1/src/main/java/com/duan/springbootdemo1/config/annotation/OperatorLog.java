package com.duan.springbootdemo1.config.annotation;

import com.duan.springbootdemo1.config.enums.OperatorLogMark;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/11/7.
 *
 * @author DuanJiaNing
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperatorLog {

    String value() default "";

    OperatorLogMark mark() default OperatorLogMark.CHANNEL;

    String[] data() default "";

}
