package com.duan.multidatasourcedemo.config.datasource;

import java.lang.annotation.*;

/**
 * Created on 2018/9/10.
 *
 * @author DuanJiaNing
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {

    /**
     * 数据源 dsKey
     */
    DsKey dsKey() default DsKey.DATASOURCE;

}
