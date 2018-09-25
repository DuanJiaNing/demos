package com.duan.springbootcommondemo.entity;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "user")
@JsonRootName("userRoot")
@JsonIgnoreProperties({"id", "time"})
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @JsonIgnore
    private String password;

    private Integer age;

    private String address;

    private Integer status;

    @JsonProperty("time")
    @JsonFormat(pattern = "yyyy-MM-DD")
    private Date createTime;

}
