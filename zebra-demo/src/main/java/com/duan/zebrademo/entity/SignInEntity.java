package com.duan.zebrademo.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created on 2018/4/19.
 *
 * @author DuanJiaNing
 */
@Data
public class SignInEntity {

    private Integer id;

    private Integer customerId;

    private Timestamp date;

    private Integer currentSignInStoreId;

    private Integer type;

    private Integer createEid;

    private Timestamp createDate;


}
