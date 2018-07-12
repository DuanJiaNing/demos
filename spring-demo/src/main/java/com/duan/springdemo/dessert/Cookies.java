package com.duan.springdemo.dessert;

import com.duan.springdemo.dessert.annotation.Multiple;
import com.duan.springdemo.dessert.annotation.Soft;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/11.
 *
 * @author DuanJiaNing
 */
@Component
@Soft
@Multiple
public class Cookies implements Dessert {
}
