package com.duan.springdemo.pad;

import com.duan.springdemo.Notepad;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created on 2018/7/12.
 *
 * @author DuanJiaNing
 */
@Configuration
public class PadConfig {

    @Bean
    @Scope("prototype")
    public Notepad myNotPad() {
        return new Notepad();
    }

}
