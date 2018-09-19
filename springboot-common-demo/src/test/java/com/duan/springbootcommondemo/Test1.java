package com.duan.springbootcommondemo;

import com.duan.springbootcommondemo.config.properties.AppInfoProperties;
import com.duan.springbootcommondemo.entity.User;
import com.duan.springbootcommondemo.service.AppInfoService;
import com.duan.springbootcommondemo.service.UserService;
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
    private UserService userService;

    @Autowired
    private AppInfoService appInfoService; // 自动装配进行注册见 com.duan.springbootcommondemo.config.auto.AppInfoServiceAutoConfiguration

    @Test
    public void test() {
        System.out.println("appService = " + appInfoProperties);
    }

    @Test
    public void test1() {
        System.out.println("appInfoService = " + appInfoService.getAppInfoProperties());
    }


    @Test
    public void test2() {
        User user = new User();
        user.setAge(112);
        user.setAddress("beijing");

        // 没有设置 name，所有会回滚
//        personService.insertPerson(user);

        // 抛异常但不会回滚
        userService.testPerson(user);

    }

}
