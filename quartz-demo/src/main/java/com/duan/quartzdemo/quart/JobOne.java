package com.duan.quartzdemo.quart;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created on 2018/6/30.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class JobOne implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(jobExecutionContext.getTrigger().getJobKey().getName() + " is schedule");
    }
}
