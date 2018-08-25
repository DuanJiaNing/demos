package com.duan.springmvcdemo.pizza.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018/8/25.
 *
 * @author DuanJiaNing
 */
public enum Topping {

    SWEET;

    public static List<Topping> asList() {
        return Arrays.asList(Topping.values());
    }

}
