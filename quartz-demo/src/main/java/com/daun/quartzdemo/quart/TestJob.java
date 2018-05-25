package com.daun.quartzdemo.quart;

import com.daun.quartzdemo.bean.RocketMqBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created on 2018/5/25.
 *
 * @author 段佳宁
 */
public class TestJob implements Job {

    @Autowired
    private RocketMqBean mqBean;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        mqBean.send(new Date());
        System.out.println(new Date() + " send data to " + mqBean.getClass());
    }

}
