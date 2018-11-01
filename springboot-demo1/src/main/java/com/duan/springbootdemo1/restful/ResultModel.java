package com.duan.springbootdemo1.restful;

import java.io.Serializable;

public class ResultModel implements Serializable {
    private static final long serialVersionUID = -8445943548965154779L;
    private int code;
    private String message;
    private Object data;

    public ResultModel() {
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.data = content;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
