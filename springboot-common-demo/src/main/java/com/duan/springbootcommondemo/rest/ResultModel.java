package com.duan.springbootcommondemo.rest;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/9/10.
 *
 * @author DuanJiaNing
 */
@Data
public class ResultModel<T extends Serializable> implements Serializable {

    private static final int succCode = 200;
    private static final int failCode = 500;

    private String message;
    private T data;
    private Integer code;

    // success
    public ResultModel(T data) {
        this.data = data;
    }

    // fail
    public ResultModel(int code) {
        this.code = code;
    }

    // success with no data
    public ResultModel() {
    }

    // fail with message
    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultModel fail() {
        return new ResultModel(failCode);
    }

}
