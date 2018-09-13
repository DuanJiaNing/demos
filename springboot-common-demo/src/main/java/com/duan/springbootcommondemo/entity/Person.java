package com.duan.springbootcommondemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created on 2018/09/13.
 *
 * @author DuanJiaNing
 */
@Data
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    private String address;

}
