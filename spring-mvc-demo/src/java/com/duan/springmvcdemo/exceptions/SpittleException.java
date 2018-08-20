package com.duan.springmvcdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created on 2018/8/20.
 *
 * @author DuanJiaNing
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "resources not find")
public class SpittleException extends RuntimeException {
}
