package com.duan.springmvcdemo.pizza;

import com.duan.springmvcdemo.entity.pizza.Customer;
import com.duan.springmvcdemo.entity.pizza.Order;
import com.duan.springmvcdemo.entity.pizza.Payment;
import com.duan.springmvcdemo.entity.pizza.PaymentDetails;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/8/24.
 *
 * @author DuanJiaNing
 */
@Component
public class PizzaFlowActions {

    public void saveOrder(Order order) {
        // TODO
    }

    public void lookupCustomer(String phoneNumber) {
        // TODO
    }

    public void checkDeliveryArea(String zipCode) {
        // TODO
    }

    public void addCustomer(Customer customer) {
        // TODO
    }

    public Payment verifyPayment(PaymentDetails details) {
        // TODO
        return null;
    }

}
