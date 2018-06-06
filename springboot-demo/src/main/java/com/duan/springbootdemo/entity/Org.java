package com.duan.springbootdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2018/6/6.
 *
 * @author DuanJiaNing
 */
@Data
@Entity
public class Org implements Serializable {
    private static final long serialVersionUID = -5976877194155361170L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer number;

    private Date createTime;

    private String intro;

}
