package com.duan.springbootdemo.handle;

import com.duan.springbootdemo.domain.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018/6/12.
 *
 * @author DuanJiaNing
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {

        return null;
    }

}
