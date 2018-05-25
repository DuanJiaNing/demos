package com.duan.zebrademo.util;

import java.util.function.Consumer;

/**
 * Created on 2018/5/11.
 *
 * @author 段佳宁
 */
public class P {

    public static Consumer<Object> o = System.out::println;

    public static Consumer<Object> ap = o -> System.out.println("----------\n"+o.toString()+"----------");

}
