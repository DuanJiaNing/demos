package com.duan.springdemo.envPro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created on 2018/7/16.
 *
 * @author DuanJiaNing
 */
@Component
@Slf4j
public class EnvOne implements BeanNameAware {

    @Autowired
    private Environment env;

    @Value("${app.version}")
    public Double version;

    public Song[] songs = {
            new Song("song 1", "artist a"),
            new Song("song 2", "artist b"),
            new Song("song 3", "artist b"),
            new Song("song 4", "artist c"),
            new Song("song 5", "artist c"),
            new Song("song 6", "artist b"),
    };

    @Override
    public void setBeanName(String name) {
        log.info(version + "");

        log.info(env.getProperty("app.version"));
        log.info(env.getProperty("pro.not.exist", "default value")); // 使用默认值
        log.info(env.getProperty("app.version", Double.class) + ""); // 属性值转为类

        try {
            env.getRequiredProperty("pro.not.exist"); // 不存在时抛出异常
        } catch (IllegalStateException e) {
            log.error(e.getLocalizedMessage());
        }

        log.info(Arrays.toString(env.getActiveProfiles())); // 获取激活的 profiles

    }

    public String getStr(boolean nullable) {
        return nullable ? null : "str";
    }
}
