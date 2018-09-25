package com.duan.springmvcdemo.entity.pizza;

import com.fasterxml.jackson.annotation.*;
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
@JsonIgnoreProperties({"cust","pizzas"}) // 不转换为 json
@JsonRootName("rootOrder") // json 根属性名
public class Order implements Serializable {

    @JsonProperty("cust") // 映射成 json 时属性名为 cust
    private Customer customer;

    @JsonIgnore // 不转换为 json
    private List<Pizza> pizzas;

    private Payment payment;

    public Order() {
        pizzas = new ArrayList<>();
        customer = new Customer();
    }

}
