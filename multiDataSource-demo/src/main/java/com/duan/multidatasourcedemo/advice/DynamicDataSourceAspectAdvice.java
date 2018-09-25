package com.duan.multidatasourcedemo.advice;

import com.duan.multidatasourcedemo.config.datasource.DataSource;
import com.duan.multidatasourcedemo.config.datasource.DynamicDataSource;
import com.duan.multidatasourcedemo.config.datasource.DynamicDataSourceRegister;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created on 2018/9/6.
 *
 * @author DuanJiaNing
 */
@Component
@Order(-10)
@Aspect
public class DynamicDataSourceAspectAdvice {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspectAdvice.class);

    @Around(value = "@annotation(dataSource)")
    public void changeDataSource(ProceedingJoinPoint point, DataSource dataSource) throws Throwable {

        String key = dataSource.value();
        if (!DynamicDataSource.contains(key)) {
            log.error("数据源 [{0}] 不存在，使用默认数据源 [{0}]", key, DynamicDataSourceRegister.defaultDataSourceKey);
            return;
        }

        String oldKey = DynamicDataSource.getDataSourceKey();
        oldKey = oldKey == null ? DynamicDataSourceRegister.defaultDataSourceKey : oldKey;

        log.info("切换数据源: [{0}] > [{1}]", oldKey, key);
        DynamicDataSource.setDataSourceKey(key);
        point.proceed();

        log.info("重置数据源: [{0}] > [{1}]", key, oldKey);
        DynamicDataSource.setDataSourceKey(oldKey);

        DynamicDataSource.clearDataSourceType();

    }

}
