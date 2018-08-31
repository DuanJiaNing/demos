package com.duan.websocketdemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2018/8/30.
 *
 * @author DuanJiaNing
 */
@Data
public class UserTextMessage implements Serializable {

    /**
     * 发送者
     */
    private Long from;

    /**
     * 发送者名称
     */
    private String fromName;

    /**
     * 接收者
     */
    private Long to;

    /**
     * 发送的文本
     */
    private String text;

    /**
     * 发送日期
     */
    private Date date;
}
