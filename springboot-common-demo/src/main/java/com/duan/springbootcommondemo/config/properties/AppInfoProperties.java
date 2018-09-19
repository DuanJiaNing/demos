package com.duan.springbootcommondemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * Created on 2018/9/10.
 *
 * @author DuanJiaNing
 */
@Data
//@Component 不能注册为 bean，否则会有两个 bean
@ConfigurationProperties(prefix = "app")
public class AppInfoProperties implements Serializable {

    private String auth;

    private String name;

}
