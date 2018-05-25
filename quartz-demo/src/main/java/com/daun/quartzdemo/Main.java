package com.daun.quartzdemo;

import com.daun.quartzdemo.quart.TestJob;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/5/25.
 *
 * @author 段佳宁
 */
@Component
public class Main implements InitializingBean {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void afterPropertiesSet() throws Exception {

        // 代表一个Quartz的独立运行容器
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        // Quartz在每次执行Job时，都重新创建一个Job实例，所以它不直接接受一个Job的实例，相反它接收一个Job实现类
        JobDetail detail = JobBuilder.newJob(TestJob.class)
                .withIdentity("test-jobdetail", "test-group")
                .build();

        detail.getJobDataMap().putAsString("self-key", false);
        detail.getJobDataMap().put("job-name", TestJob.class.getName());

        ScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(new CronExpression("/3 * * * * ? "));

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("test-trigger", "test-group")
                .withSchedule(cronSchedule)
                .build();

        scheduler.scheduleJob(detail,trigger);

    }

}
