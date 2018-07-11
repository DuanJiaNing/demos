package com.duan.springdemo;

import com.duan.springdemo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created on 2018/6/25.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class Main {


    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }

}
