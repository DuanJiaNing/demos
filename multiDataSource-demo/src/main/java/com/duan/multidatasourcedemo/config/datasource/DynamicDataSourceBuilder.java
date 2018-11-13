package com.duan.multidatasourcedemo.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/9/6.
 *
 * @author DuanJiaNing
 * @see com.duan.multidatasourcedemo.config.datasource.DataSource
 */
@Component
public class DynamicDataSourceBuilder implements EnvironmentAware {

    // 如配置文件中未指定数据源类型，使用这些默认值
    public static final String defaultDataSourceKey = "datasource";
    private static final String defaultDataSourceType = "com.alibaba.druid.pool.DruidDataSource";
    private static final String defaultDataSourcePrefix = "spring.datasource";

    // 定制数据源参数命名遵循如下规则：spring.datasource.multi.dskey.propertieskey, 如 spring.datasource.multi.ds1.url
    private static final String customDataSourcePrefix = "spring.datasource.multi";

    // 定制数据源 key 定义，即 dskey 部分，多个数据源用 "," 号隔开
    private static final String customDataSourceKeys = customDataSourcePrefix + ".ds-keys";

    private DataSource defaultDataSource; // 默认数据源
    private Map<String, DataSource> targetDataSources = new HashMap<>(); // 动态数据源

    /**
     * 加载主默认源配置.
     */
    private void initDefaultDataSource(Environment env) {
        defaultDataSource = buildDataSource(env, defaultDataSourcePrefix);
        dataBinder(defaultDataSource, env);

        // 将主数据源添加到数据源库中
        targetDataSources.put(defaultDataSourceKey, defaultDataSource);
    }

    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }

    private class DataSourcePropertyKey {
        // properties 对应 key 名
        static final String url = "url";
        static final String username = "username";
        static final String password = "password";
        static final String driverClassName = "driver-class-name";
        static final String type = "type";
    }

    /**
     * 初始化定制数据源
     */
    private void initCustomDataSources(Environment env) {

        // 读取配置文件获取定制数据源，也可以通过数据库获取数据源
        String dsNames = env.getProperty(customDataSourceKeys);

        for (String dsKey : dsNames.split(",")) {
            DataSource ds = buildDataSource(env, customDataSourcePrefix + "." + dsKey);
            targetDataSources.put(dsKey, ds);
            dataBinder(ds, env);
        }

    }

    /**
     * 创建 datasource.
     */
    @SuppressWarnings("unchecked")
    private DataSource buildDataSource(Environment env, String dsPrefix) {

        try {

            String prefix = dsPrefix + ".";
            String dbpType = env.getProperty(prefix + DataSourcePropertyKey.type, defaultDataSourceType);
            Class<? extends DataSource> dsType = (Class<? extends DataSource>) Class.forName(dbpType);

            DataSource dataSource = DataSourceBuilder.create()
                    .driverClassName(env.getProperty(prefix + DataSourcePropertyKey.driverClassName))
                    .url(env.getProperty(prefix + DataSourcePropertyKey.url))
                    .username(env.getProperty(prefix + DataSourcePropertyKey.username))
                    .password(env.getProperty(prefix + DataSourcePropertyKey.password))
                    .type(dsType)
                    .build();

            configDataSourcePool(dataSource);
            return dataSource;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private void configDataSourcePool(DataSource source) {
        if (source instanceof DruidDataSource) {
            //
        }
    }

    /**
     * 为 DataSource 绑定数据
     */
    private void dataBinder(DataSource dataSource, Environment env) {

        // https://github.com/spring-projects/spring-boot/wiki/Relaxed-Binding-2.0
        // 从配置中读取时配置名要遵循 “uniform ”
        // 基础配置
        String[] dkeys = {
                defaultDataSourcePrefix + ".filters",
                defaultDataSourcePrefix + ".max-active",
                defaultDataSourcePrefix + ".initial-size",
                defaultDataSourcePrefix + ".max-wait",
                defaultDataSourcePrefix + ".min-idle",
                defaultDataSourcePrefix + ".time-between-eviction-runs-millis",
                defaultDataSourcePrefix + ".min-evictable-idle-time-millis",
                defaultDataSourcePrefix + ".validation-query",
                defaultDataSourcePrefix + ".test-while-idle",
                defaultDataSourcePrefix + ".test-on-borrow",
                defaultDataSourcePrefix + ".test-on-return",
                defaultDataSourcePrefix + ".pool-prepared-statements",
                defaultDataSourcePrefix + ".max-open-prepared-statements"};

        Map<String, Object> def = new HashMap<String, Object>();
        for (String dkey : dkeys) {
            def.put(dkey, env.getProperty(dkey));
        }

        PropertyValues dataSourcePropertyValues = new MutablePropertyValues(def);
        DataBinder dataBinder = new DataBinder(dataSource);
        dataBinder.bind(dataSourcePropertyValues);

    }

    public DataSource getDefaultDataSource() {
        return defaultDataSource;
    }

    public Map<String, DataSource> getTargetDataSources() {
        return targetDataSources;
    }

}