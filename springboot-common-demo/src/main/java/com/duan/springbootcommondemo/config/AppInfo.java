package com.duan.springbootcommondemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created on 2018/9/10.
 *
 * @author DuanJiaNing
 */
@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppInfo implements Serializable {

    private String auth;

    private String name;

}
