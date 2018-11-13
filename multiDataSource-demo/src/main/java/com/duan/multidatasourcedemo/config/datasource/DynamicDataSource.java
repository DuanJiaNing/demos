package com.duan.multidatasourcedemo.config.datasource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2018/9/6.
 *
 * @author DuanJiaNing
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DsKey> dataSourceKey = new InheritableThreadLocal<DsKey>() {
        @Override
        protected DsKey initialValue() {
            return DsKey.DATASOURCE;
        }
    };
    @Autowired
    private DynamicDataSourceBuilder dynamicDataSourceBuilder;

    // 维护数据源 key（Lookup key）
    private static Set<String> dsKeySet = new HashSet<>();

    public static DsKey getDataSourceKey() {
        return dataSourceKey.get();
    }
    public static void setDataSourceKey(DsKey key) {
        dataSourceKey.set(key);
    }

    static int addDsKey(String key) {
        dsKeySet.add(key);
        return dsKeySet.size();
    }
    public static void clearDataSourceType() {
        dataSourceKey.remove();
    }
    public static boolean contains(String key) {
        return dsKeySet.contains(key);
    }
    public static boolean contains(DsKey key) {
        return dsKeySet.contains(key.getCode());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

    @Override
    public void afterPropertiesSet() {

        // 所有数据源，包括默认数据源
        Map<String, DataSource> customDataSources = dynamicDataSourceBuilder.getTargetDataSources();

        // Lookup key
        customDataSources.forEach((key, da) -> addDsKey(key));

        // DataSource
        DataSource defaultDataSource = dynamicDataSourceBuilder.getDefaultDataSource();
        setDefaultTargetDataSource(defaultDataSource);
        setTargetDataSources(new HashMap<>(customDataSources));

        super.afterPropertiesSet();
    }
}
