package com.duan.springdemo.conditional;

import com.duan.springdemo.player.CDPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created on 2018/7/2.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class MyConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        BeanDefinitionRegistry registry = context.getRegistry();
        log.warn(String.join(",", registry.getBeanDefinitionNames()));
        log.warn(registry.getBeanDefinitionCount() + "");

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        CDPlayer cdPlayer = (CDPlayer) beanFactory.getBean("CDPlayer");
        log.warn(cdPlayer.toString());

        ClassLoader classLoader = context.getClassLoader();
        log.warn(classLoader.getParent().toString());

        ResourceLoader resourceLoader = context.getResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:spring-config.xml");
        log.warn(resource.getDescription());

        Environment env = context.getEnvironment();
        String version = env.getProperty("app.version");
        log.warn(version);

        /*log.warn(metadata.isAnnotated("Bean") + "");
        log.warn(metadata.getAnnotationAttributes("Bean").toString());*/

        return true;
    }

}
