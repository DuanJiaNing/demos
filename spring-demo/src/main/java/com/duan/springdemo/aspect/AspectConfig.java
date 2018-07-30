package com.duan.springdemo.aspect;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created on 2018/7/24.
 *
 * @author DuanJiaNing
 */
@Configuration
//@EnableAspectJAutoProxy // 使用 xml
@ImportResource("classpath:spring-aop.xml")
public class AspectConfig {

}
