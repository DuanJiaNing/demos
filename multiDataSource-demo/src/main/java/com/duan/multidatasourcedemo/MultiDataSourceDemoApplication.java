package com.duan.multidatasourcedemo;

import com.duan.multidatasourcedemo.config.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created on 2018/9/25.
 *
 * @author DuanJiaNing
 */
@SpringBootApplication
@Import({DynamicDataSourceRegister.class})
public class MultiDataSourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceDemoApplication.class, args);
    }

}
