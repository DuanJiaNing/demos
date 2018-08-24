package com.duan.springmvcdemo.pizza.domain;

import java.io.Serializable;

/**
 * Created on 2018/8/24.
 *
 * @author DuanJiaNing
 */
public class Customer implements Serializable {

    private String phoneNumber;

    private String zipCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
