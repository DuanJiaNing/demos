package com.duan.websocketdemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/8/31.
 *
 * @author DuanJiaNing
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

}
