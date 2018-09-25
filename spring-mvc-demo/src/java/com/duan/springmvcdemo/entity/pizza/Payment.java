package com.duan.springmvcdemo.entity.pizza;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/8/24.
 *
 * @author DuanJiaNing
 */
@Data
@JsonIgnoreType // 不转换 Payment 类
public class Payment implements Serializable {
}
