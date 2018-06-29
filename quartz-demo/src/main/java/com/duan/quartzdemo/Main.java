package com.duan.quartzdemo;

import com.duan.quartzdemo.entity.ScheduleJob;
import com.duan.quartzdemo.quart.QuartService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/5/25.
 *
 * @author 段佳宁
 */
@Component
public class Main implements InitializingBean {

    @Autowired
    private QuartService quartService;

    @Override
    public void afterPropertiesSet() throws Exception {

        quartService.addJob(new ScheduleJob(
                "job-1",
                "group-1",
                ScheduleJob.STATUS_RUNNING,
                "/3 * * * * ? "), System.out::println);

    }

}
