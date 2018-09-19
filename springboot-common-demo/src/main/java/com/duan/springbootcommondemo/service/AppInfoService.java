package com.duan.springbootcommondemo.service;

import com.duan.springbootcommondemo.config.properties.AppInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018/9/11.
 *
 * @author DuanJiaNing
 */
public class AppInfoService {

    @Autowired
    private AppInfoProperties appInfoProperties;

    public AppInfoProperties getAppInfoProperties() {
        return appInfoProperties;
    }
}
