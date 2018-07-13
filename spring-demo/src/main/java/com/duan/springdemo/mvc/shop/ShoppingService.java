package com.duan.springdemo.mvc.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/7/13.
 *
 * @author DuanJiaNing
 */
@Service // 单例
public class ShoppingService {

    // 一次请求相关的实例
    private ShoppingCart2 shoppingCart2;

    // 在使用时，代理将会调用一个会话相关的实例（一个会话创建一个）
    private ShoppingCart shoppingCart;

    @Autowired // 创建实例时注入的是一个代理 JDK 代理
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Autowired // 同样注入的是代理 CGLib 代理
    public void setShoppingCart2(ShoppingCart2 shoppingCart2) {
        this.shoppingCart2 = shoppingCart2;
    }
}
