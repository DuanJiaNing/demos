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
 * DI 时序
 * 1 JVM load class -> initClass(构造代码块+构造器+成员变量实例化)
 * 2 BeanNameAware#setBeanName
 * 3 BeanFactoryAware#setBeanFactory
 * 4 ApplicationContextAware#setApplicationContext
 * 5 @PostConstruct#init
 * 6 InitializingBean#afterPropertiesSet
 * 7 BeanPostProcessor#postProcessBeforeInitialization
 * 8 BeanPostProcessor#postProcessAfterInitialization
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

    static {
        System.out.println("CDPlayer: load class static{}");
    }

    {
        // 1
        System.out.println("CDPlayer: step 1 {}");
    }

    public CDPlayer() {
        System.out.println("CDPlayer: step constructer");
    }

    @PostConstruct
    public void init() {
        // 5
        System.out.println("CDPlayer: step 5 init ");
    }

    @Override
    public void play() {
        disc.play(1);
    }

    @Override
    public void setBeanName(String name) {
        // 2
        System.out.println("CDPlayer: step 2 setBeanName=" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // 3
        System.out.println("CDPlayer: step 3 setBeanFactory=" + beanFactory.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 6
        System.out.println("CDPlayer: step 6 afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 4
        System.out.println("CDPlayer: step 4 setApplicationContext=" + applicationContext.toString());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 7
        System.out.println("CDPlayer: step 7 postProcessBeforeInitialization=" + beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 8
        System.out.println("CDPlayer: step 8 postProcessAfterInitialization=" + beanName);
        return null;
    }

    @Override
    public void destroy() throws Exception {
        // 9
        System.out.println("CDPlayer: step 9 destroy");
    }
}
