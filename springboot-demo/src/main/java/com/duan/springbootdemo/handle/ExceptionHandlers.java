package com.duan.springbootdemo.handle;

import com.duan.springbootdemo.domain.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018/6/12.
 *
 * @author DuanJiaNing
 */
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        final Result result = new Result();
        result.setData(e.getMessage());
        return result;
    }

}
