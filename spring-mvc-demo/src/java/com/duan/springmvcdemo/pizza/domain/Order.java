package com.duan.springmvcdemo.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/8/24.
 *
 * @author DuanJiaNing
 */
public class Order implements Serializable {

    private Customer customer;

    private List<Pizza> pizzas;

    private Payment payment;

    public Order() {
        pizzas = new ArrayList<>();
        customer = new Customer();
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
