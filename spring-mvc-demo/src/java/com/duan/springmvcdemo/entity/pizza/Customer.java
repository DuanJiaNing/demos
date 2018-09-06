package com.duan.springmvcdemo.entity.pizza;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/8/24.
 *
 * @author DuanJiaNing
 */
@Data
public class Customer implements Serializable {

    private String phoneNumber;

    private String zipCode;

}
