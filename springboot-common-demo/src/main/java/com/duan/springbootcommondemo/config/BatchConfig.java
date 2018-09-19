package com.duan.springbootcommondemo.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
//
//
//    @Bean
//    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
//        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setTransactionManager(transactionManager);
//        factoryBean.setDatabaseType("mysql");
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
//        return jobLauncher;
//    }
//
//    @Bean
//    public Job importJob(JobBuilderFactory jobs, Step s1) {
//        return jobs.get("importJob")
//                .incrementer(new RunIdIncrementer())
//    }

}
