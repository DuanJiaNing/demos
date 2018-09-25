package com.duan.multidatasourcedemo.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/9/6.
 * 将默认数据源和定制数据源添加到 IOC 容器
 *
 * @author DuanJiaNing
 * @see com.duan.multidatasourcedemo.config.datasource.DataSource
 */
@Configuration
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    public static final String defaultDataSourceKey = "datasource";
    // 如配置文件中未指定数据源类型，使用该默认值
//    private static final String defaultDataSourceType = "org.apache.tomcat.jdbc.pool.DataSource";
    private static final String defaultDataSourceType = "com.alibaba.druid.pool.DruidDataSource";
    private static final String defaultDataSourcePrefix = "spring.datasource";
    // 定制数据源参数命名遵循如下规则：spring.datasource.multi.dskey.propertieskey, 如 spring.datasource.multi.ds1.url
    private static final String customDataSourcePrefix = "spring.datasource.multi";

    // 定制数据源 key 定义，即 dskey 部分，多个数据源用 "," 号隔开
    private static final String customDataSourceKeys = customDataSourcePrefix + ".ds-keys";

    // 默认数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<>();

    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }

    /**
     * 加载主数据源配置.
     */
    private void initDefaultDataSource(Environment env) {
        // 创建默认数据源
        defaultDataSource = buildDataSource(env, defaultDataSourcePrefix);
        dataBinder(defaultDataSource, env);
    }

    /**
     * 初始化定制数据源
     */
    private void initCustomDataSources(Environment env) {

        // 读取配置文件获取定制数据源，也可以通过数据库获取数据源
        String dsNames = env.getProperty(customDataSourceKeys);

        for (String dsKey : dsNames.split(",")) {
            DataSource ds = buildDataSource(env, customDataSourcePrefix + "." + dsKey);
            customDataSources.put(dsKey, ds);
            dataBinder(ds, env);
        }
    }

    /**
     * 创建 datasource.
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Environment env, String dsPrefix) {
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, dsPrefix + ".");

        try {

            String dbpType = resolver.getProperty(DataSourcePropertyKey.type, defaultDataSourceType);
            Class<? extends DataSource> dsType = (Class<? extends DataSource>) Class.forName(dbpType);

            DataSource dataSource = DataSourceBuilder.create()
                    .driverClassName(resolver.getProperty(DataSourcePropertyKey.driverClassName))
                    .url(resolver.getProperty(DataSourcePropertyKey.url))
                    .username(resolver.getProperty(DataSourcePropertyKey.username))
                    .password(resolver.getProperty(DataSourcePropertyKey.password))
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
            // TODO
        }
    }

    /**
     * 为 DataSource 绑定数据
     */
    private void dataBinder(DataSource dataSource, Environment env) {

        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(new DefaultConversionService());
        dataBinder.setIgnoreNestedProperties(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);

        Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
        Map<String, Object> values = new HashMap<>(rpr);

        // 重置已经设置的属性
        values.remove("type");
        values.remove("driverClassName");
        values.remove("url");
        values.remove("username");
        values.remove("password");

        PropertyValues dataSourcePropertyValues = new MutablePropertyValues(values);

        dataBinder.bind(dataSourcePropertyValues);

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<Object, Object> targetDataSources = new HashMap<>();

        // 将主数据源添加到数据源库中
        targetDataSources.put(defaultDataSourceKey, defaultDataSource);
        DynamicDataSource.addDsKey(defaultDataSourceKey);

        // 添加定制数据源
        targetDataSources.putAll(customDataSources);
        customDataSources.keySet().forEach(DynamicDataSource::addDsKey);

        // 创建 DynamicDataSource
        GenericBeanDefinition bean = new GenericBeanDefinition();
        bean.setBeanClass(DynamicDataSource.class);
        bean.setSynthetic(true);

        MutablePropertyValues mpv = bean.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);

        registry.registerBeanDefinition("dataSource", bean);
    }

    private class DataSourcePropertyKey {
        // properties 对应 key 名
        static final String url = "url";
        static final String username = "username";
        static final String password = "password";
        static final String driverClassName = "driver-class-name";
        static final String type = "type";
    }

}