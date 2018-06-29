package com.duan.quartzdemo;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/6/29.
 *
 * @author DuanJiaNing
 */
@Configuration
@ComponentScan(basePackages = {"com.duan.quartzdemo.xxl"})
public class AppConfig {

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private Integer port;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Bean
    public XxlJobExecutor xxlJobExecutor() throws Exception {
        XxlJobExecutor executor = new XxlJobExecutor();
        executor.setIp(ip);
        executor.setPort(port);
        executor.setAppName(appName);
        executor.setAdminAddresses(adminAddresses);
        executor.setLogPath(logPath);
        executor.setAccessToken(accessToken);
        executor.start();

        return executor;
    }

}
