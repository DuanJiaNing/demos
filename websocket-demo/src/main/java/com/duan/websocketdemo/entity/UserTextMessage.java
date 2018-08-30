package com.duan.websocketdemo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2018/8/30.
 *
 * @author DuanJiaNing
 */
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

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
