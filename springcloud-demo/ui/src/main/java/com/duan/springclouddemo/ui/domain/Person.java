package com.duan.springclouddemo.ui.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@Data
public class Person {

    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private String address;

    private Integer status;

    private Date createTime;

}
