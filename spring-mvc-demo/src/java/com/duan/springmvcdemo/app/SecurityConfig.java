package com.duan.springmvcdemo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 内存认证
        auth.inMemoryAuthentication()
                .withUser("user").password("abc").roles("USER").and()
                .withUser("admin").password("abc").roles("USER", "ADMIN");


        // jdbc 数据库认证
        String idForEncode = "pwdEncode_1";
        Map<String, PasswordEncoder> map = new HashMap<>();
        map.put(idForEncode, new BCryptPasswordEncoder(10));

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,true from spitter where username=?")
                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from spitter where username=?")
                .passwordEncoder(new DelegatingPasswordEncoder(idForEncode, map))
        /*.groupAuthoritiesByUsername()*/;


        // ldap 认证
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .contextSource().url("ldap://habuma.com:389/dc=habuma,dc=com").and()
                .passwordCompare()
                .passwordEncoder(new DelegatingPasswordEncoder(idForEncode, map))
                .passwordAttribute("password");

    }

    @Override // 配置 filter 链
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override // 配置规则
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }


}
