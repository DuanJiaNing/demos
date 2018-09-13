package com.duan.springbootcommondemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created on 2018/9/12.
 *
 * @author DuanJiaNing
 */
@Configuration
@EnableJpaRepositories("com.duan.springbootcommondemo.dao")
public class JpaConfiguration {

}
