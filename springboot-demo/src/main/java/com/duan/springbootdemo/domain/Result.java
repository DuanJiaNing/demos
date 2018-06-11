package com.duan.springbootdemo.domain;

import lombok.Data;

/**
 * Created on 2018/6/11.
 *
 * @author DuanJiaNing
 */
@Data
public class Result<T> {

    /**
     * 错误代码 成功返回 0
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

}
