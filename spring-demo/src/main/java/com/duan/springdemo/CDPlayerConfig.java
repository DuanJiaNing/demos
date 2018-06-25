package com.duan.springdemo;

import com.duan.springdemo.disc.CompactDisc;
import com.duan.springdemo.disc.SgtPeppers;
import com.duan.springdemo.player.CDPlayer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/6/25.
 *
 * @author DuanJiaNing
 */
@Configuration
@ComponentScan("com.duan.springdemo")
public class CDPlayerConfig {

    //    @Bean(name = "peppers")
    @Bean // Bean id 与方法名相同
    public CompactDisc setPeppers() {
        return new SgtPeppers();
    }

    @Bean
    public CDPlayer cdPlayer(@Qualifier("setPeppers") CompactDisc disc) {
        return new CDPlayer(disc);
    }

}
