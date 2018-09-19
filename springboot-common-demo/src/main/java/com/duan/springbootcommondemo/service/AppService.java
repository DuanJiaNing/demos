package com.duan.springbootcommondemo.service;

import com.duan.springbootcommondemo.config.properties.AppInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/9/10.
 *
 * @author DuanJiaNing
 */
@Service
public class AppService {

    @Autowired
    private AppInfoProperties appInfoProperties;

    public AppInfoProperties getAppInfoProperties() {
        return appInfoProperties;
    }

}
