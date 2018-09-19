package com.duan.springbootcommondemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSource dataSource2;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 内存用户
        auth.inMemoryAuthentication()
                .withUser("duan").password("duan123456").roles("USER", "ADMIN").and()
                .withUser("tom").password("tom123456").roles("USER");

        // jdbc 用户
        auth.jdbcAuthentication()
                .dataSource(dataSource).withUser("duan").and()
                .dataSource(dataSource2).withUser("tom").and()
                .dataSource(dataSource)
                .usersByUsernameQuery("select name from myusers where age > ?")
                .authoritiesByUsernameQuery("select role from myroles where name = ?");

    }
}
