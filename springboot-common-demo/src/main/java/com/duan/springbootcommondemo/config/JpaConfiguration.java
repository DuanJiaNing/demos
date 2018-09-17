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
//@EnableTransactionManagement // 开启声明式事务，扫描 @Transactional spring boot 自动进行配置
public class JpaConfiguration {

}
