package com.duan.springdemo.dessert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/11.
 *
 * @author DuanJiaNing
 */
@Component
public class DessertProvider {

    @Autowired
    private Dessert dessert;

}
