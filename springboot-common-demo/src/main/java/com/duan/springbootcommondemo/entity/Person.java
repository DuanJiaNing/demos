package com.duan.springbootcommondemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/9/12.
 *
 * @author DuanJiaNing
 */
@Data
public class Person implements Serializable {

    private Long id;
    private String name;

}
