package com.duan.springbootcommondemo.config.auto;

import com.duan.springbootcommondemo.config.properties.AppInfoProperties;
import com.duan.springbootcommondemo.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/9/11.
 *
 * @author DuanJiaNing
 */
@Configuration
@EnableConfigurationProperties(AppInfoProperties.class)
@ConditionalOnClass(AppInfoService.class) // 类路径下有该类
// 前置 app 开头的有配置即为 enabled
@ConditionalOnProperty(prefix = "app", value = "enable", matchIfMissing = true)
public class AppInfoServiceAutoConfiguration { // META-INF/spring.factories 中的注册不是必须的

    @Autowired
    private AppInfoProperties properties;

    @Bean
    @ConditionalOnMissingBean(AppInfoService.class) // 容器中没有该 bean 时装配
    public AppInfoService appInfoService() {
        return new AppInfoService();
    }
}
