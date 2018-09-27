package com.duan.springbootcommondemo.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created on 2018/9/27.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class ApplicationRunListener implements SpringApplicationRunListener {

    public ApplicationRunListener(SpringApplication app, String[] args) {
        System.out.println("ApplicationRunListener.ApplicationRunListener " + app.getClass().getCanonicalName());
    }

    @Override
    public void starting() {
        System.out.println("嘿，SpringBoot应用要开始启动咯！”");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
        log.info("environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {

    }
}
