package com.duan.springbootcommondemo.config;

import com.duan.springbootcommondemo.config.security.SpecSecurityInterceptor;
import com.duan.springbootcommondemo.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
// @EnableWebSecurity spring boot 继承 WebSecurityConfigurerAdapter 即可
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SpecSecurityInterceptor specSecurityInterceptor;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);

//         内存用户
//        auth.inMemoryAuthentication()
//                .withUser("duan").password("duan123456").roles("USER", "ADMIN").and()
//                .withUser("tom").password("tom123456").roles("USER");

        // jdbc 用户
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select name from person where age > ?")
//                .authoritiesByUsernameQuery("select role from myroles where name = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                // 可匿名访问，无权限
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/home",
                        "/index",
                        "/test/**"
                ).anonymous()

                .anyRequest() // 其余所有请求都需要认证（登录）认证后才可访问
                .authenticated()

                // 定制规则 用注解实现 @PreAuthorize("hasRole('USER')")
                .antMatchers("/user/**").hasAnyRole(RoleEnum.USER.getCode(), RoleEnum.MANAGER.getCode())
                .antMatchers("/admin/**").hasRole(RoleEnum.MANAGER.getCode())

                // 登录设置
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() // granted access to any user.

                // 登出设置
                .and()
                .logout()
                .permitAll() // granted access to any user.

                // 关闭 csrf
                .and()
                .csrf().disable();

        // https://blog.csdn.net/u012373815/article/details/54633046
        http.addFilterBefore(specSecurityInterceptor, FilterSecurityInterceptor.class);

    }
}
