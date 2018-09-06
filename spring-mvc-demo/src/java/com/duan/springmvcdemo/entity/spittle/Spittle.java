package com.duan.springmvcdemo.entity.spittle;

import lombok.Data;

import java.util.Date;

/**
 * Created on 2018/8/15.
 *
 * @author DuanJiaNing
 */
@Data
public class Spittle {
    private final Long id;
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double latitude, Double longitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
