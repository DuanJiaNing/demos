package com.duan.zebrademo.dao;

import com.duan.zebrademo.entity.SignInEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created on 2018/4/19.
 *
 * @author DuanJiaNing
 */
@Repository
public interface SignInDao {

    List<SignInEntity> listAll();

    void insert(SignInEntity entity);

    SignInEntity get(int id);

    int delete(int id);

    int updateSigninTime(@Param("id") int id, @Param("newSigninTime") Timestamp newSigninTime);

    List<SignInEntity> listByCustomerId(int customerId);

    List<SignInEntity>  testSelect();

}
