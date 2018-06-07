package com.duan.springbootdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
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

    private Integer mobile;

    private Date createTime;

    private String intro;

    @Min(value = 18, message = "最小年龄为 18 岁")
    private Integer age;

    private String email;

}
