package com.duan.springbootcommondemo.config.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created on 2018/9/27.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class AppContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
      log.info(context.toString());
    }

}
