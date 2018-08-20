package com.duan.springmvcdemo.controller.handler;

import com.duan.springmvcdemo.exceptions.DuplicateSpittleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created on 2018/8/20.
 *
 * @author DuanJiaNing
 */
@ControllerAdvice
public class AppExceptionHandler {

    // 针对所有控制器的 DuplicateSpittleException 异常
    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleException() {
        return "error/duplicate";
    }

}
