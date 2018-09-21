package com.duan.springclouddemo.person.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private String address;

    private Integer status;

    private Date createTime;

}
