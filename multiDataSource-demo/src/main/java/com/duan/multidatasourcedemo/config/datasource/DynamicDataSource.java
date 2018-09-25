package com.duan.multidatasourcedemo.config.datasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2018/9/6.
 *
 * @author DuanJiaNing
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();
    // 维护数据源 key（name）
    private static Set<String> dsKeySet = new HashSet<>();

    public static String getDataSourceKey() {
        return dataSourceKey.get();
    }

    public static void setDataSourceKey(String key) {
        dataSourceKey.set(key);
        dsKeySet.add(key);
    }

    public static int addDsKey(String key) {
        dsKeySet.add(key);
        return dsKeySet.size();
    }

    public static void clearDataSourceType() {
        dataSourceKey.remove();
    }

    public static boolean contains(String key) {
        return dsKeySet.contains(key);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

}
