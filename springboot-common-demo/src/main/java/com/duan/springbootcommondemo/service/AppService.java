package com.duan.springbootcommondemo.service;

import com.duan.springbootcommondemo.config.AppInfo;
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
    private AppInfo appInfo;

    public AppInfo getAppInfo() {
        return appInfo;
    }
}
