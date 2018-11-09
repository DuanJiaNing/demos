package com.duan.multidatasourcedemo.service;

import com.duan.multidatasourcedemo.config.datasource.DataSource;
import com.duan.multidatasourcedemo.config.datasource.DsKey;
import com.duan.multidatasourcedemo.config.datasource.DynamicDataSource;

/**
 * Created on 2018/11/9.
 *
 * @author DuanJiaNing
 */
public class TestService {


    @DataSource(dsKey = DsKey.DB1)
    public void test() {
        //
    }

    @DataSource(dsKey = DsKey.DB1)
    public void test1() {
        DynamicDataSource.setDataSourceKey(DsKey.DATASOURCE);
        // do somthing
        DynamicDataSource.setDataSourceKey(DsKey.DB1);
    }


}
