package com.duan.springmvcdemo.entity.pizza;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/8/24.
 *
 * @author DuanJiaNing
 */
@Data
public class Order implements Serializable {

    private Customer customer;

    private List<Pizza> pizzas;

    private Payment payment;

    public Order() {
        pizzas = new ArrayList<>();
        customer = new Customer();
    }

}
