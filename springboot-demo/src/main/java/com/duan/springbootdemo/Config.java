package com.duan.springbootdemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/6/2.
 *
 * @author DuanJiaNing
 */
//@PropertySource("classpath:application.yml")
@Component
@ConfigurationProperties(prefix = "pro")
@Data
public class Config {

    private String age;

    private String sex;

}
