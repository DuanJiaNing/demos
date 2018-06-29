package com.duan.quartzdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2018/6/29.
 *
 * @author DuanJiaNing
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleJob {

    public static final int STATUS_RUNNING = 1;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务状态 是否启动任务
     */
    private Integer jobStatus;

    /**
     * cron表达式
     */
    private String cronExpression;

}