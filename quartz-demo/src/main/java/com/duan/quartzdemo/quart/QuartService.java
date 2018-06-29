package com.duan.quartzdemo.quart;

import com.duan.quartzdemo.entity.ScheduleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/6/29.
 *
 * @author DuanJiaNing
 */
@Service
public class QuartService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 添加一个任务到 quartz
     */
    public void addJob(ScheduleJob scheduleJob, Job job) throws SchedulerException {
        if (scheduleJob == null || scheduleJob.getJobStatus() != ScheduleJob.STATUS_RUNNING) {
            return;
        }

        // 代表一个 Quartz 的独立运行容器
        // 获取调度器
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        // 唯一标识一个触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {

            // 新建 corn trigger
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
                    .withSchedule(scheduleBuilder)
                    .build();

            // Quartz 在每次执行 Job 时，都重新创建一个 Job 实例，所以它不直接接受一个 Job 的实例，相反它接收一个 Job 实现类
            JobDetail jobDetail = JobBuilder
                    .newJob(job.getClass())
                    .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
                    .build();
            jobDetail.getJobDataMap().put("scheduleJob", scheduleJob); // 把任务参数绑定到 任务数据 map 中

            // 关联触发器和任务
            scheduler.scheduleJob(jobDetail, trigger);

        } else { // trigger 已存在，那么更新相应的定时设置

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

            // 按新的 cronExpression 表达式重新构建 trigger
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();

            // 按新的 trigger 重新设置 scheduleJob 执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 暂停一个job
     */
    public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.pauseJob(jobKey);
    }


    /**
     * 恢复一个job
     */
    public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除一个job
     */
    public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 立即执行job
     */
    public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

}
