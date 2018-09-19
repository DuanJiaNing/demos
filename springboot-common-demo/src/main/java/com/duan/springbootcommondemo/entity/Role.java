package com.duan.springbootcommondemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2018/09/13.
 *
 * @author DuanJiaNing
 */
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String des;

    private String code;

    private Date createTime;

}
