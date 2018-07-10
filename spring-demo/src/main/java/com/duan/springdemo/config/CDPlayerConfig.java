package com.duan.springdemo.config;

import com.duan.springdemo.conditional.MyConditional;
import com.duan.springdemo.disc.CompactDisc;
import com.duan.springdemo.disc.MyDisc;
import com.duan.springdemo.disc.SgtPeppers;
import com.duan.springdemo.player.CDPlayer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created on 2018/6/25.
 *
 * @author DuanJiaNing
 */
@Configuration
@Profile("dev") // 这个 config 只有在 dev profile 激化时其中的 bean 才会创建
public class CDPlayerConfig {

    //    @Bean(name = "peppers")
    @Bean // Bean id 与方法名相同
    public CompactDisc setPeppers() {
        return new SgtPeppers();
    }

    @Bean
    public CDPlayer cdPlayer(@Qualifier("setPeppers") CompactDisc disc) {
        return new CDPlayer();
    }

    @Bean
    @Conditional(MyConditional.class)
    public MyDisc myDisc() {
        return new MyDisc();
    }

}


