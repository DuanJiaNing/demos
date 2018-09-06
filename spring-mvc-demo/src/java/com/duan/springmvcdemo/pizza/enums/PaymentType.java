package com.duan.springmvcdemo.pizza.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018/8/25.
 *
 * @author DuanJiaNing
 */
public enum PaymentType {

    CARD;

    public static List<PaymentType> asList() {
        return Arrays.asList(PaymentType.values());
    }

}
