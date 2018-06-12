package com.duan.springbootdemo;

import com.duan.springbootdemo.service.OrgService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2018/6/12.
 *
 * @author DuanJiaNing
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgServiceTest {

    @Autowired
    private OrgService orgService;

    @Test
    public void test() {
        Assert.assertNotNull(orgService.findOne(2));
    }


}
