package com.duan.springbootcommondemo;

import com.duan.springbootcommondemo.config.AppInfoProperties;
import com.duan.springbootcommondemo.service.AppInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018/9/11.
 *
 * @author DuanJiaNing
 */
public class Test1 extends BaseTest {

    @Autowired
    private AppInfoProperties appInfoProperties;

    @Autowired
    private AppInfoService appInfoService; // 自动装配进行注册见 com.duan.springbootcommondemo.config.AppInfoServiceAutoConfiguration

    @Test
    public void test() {
        System.out.println("appService = " + appInfoProperties);
    }

    @Test
    public void test1() {
        System.out.println("appInfoService = " + appInfoService.getAppInfoProperties());
    }

}
