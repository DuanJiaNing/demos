package com.duan.websocketdemo.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WebSocketHandler {
}
