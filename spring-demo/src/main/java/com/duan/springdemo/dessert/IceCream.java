package com.duan.springdemo.dessert;

import com.duan.springdemo.dessert.annotation.Cold;
import com.duan.springdemo.dessert.annotation.Creamy;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/11.
 *
 * @author DuanJiaNing
 */
@Component
@Creamy
@Cold
public class IceCream implements Dessert {
}
