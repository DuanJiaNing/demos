package com.duan.springdemo.player;

import com.duan.springdemo.disc.CompactDisc;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created on 2018/6/25.
 *
 * @author DuanJiaNing
 */
@Component
public class CDPlayer implements
        MediaPlayer,
        BeanNameAware,
        BeanFactoryAware,
        ApplicationContextAware,
        BeanPostProcessor,
        InitializingBean,
        DisposableBean {

    @Resource(name = "sgtPeppers")
    private CompactDisc disc;

    {
        // 1
    }

    @PostConstruct
    public void init() {
        // 7
    }

    @Override
    public void play() {
        disc.play();
    }

    @Override
    public void setBeanName(String name) {
        // 2
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // 3
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 6
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 4
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 5
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 8
        return null;
    }

    @Override
    public void destroy() throws Exception {
        // 9
    }
}
