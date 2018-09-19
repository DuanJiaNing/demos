package com.duan.springbootcommondemo.dao;

import com.duan.springbootcommondemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created on 2018/9/12.
 *
 * @author DuanJiaNing
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);

    List<User> findFirst10ByIdGreaterThan(Integer id);

    @Query("select id,name,age,address from User where name = :name and age > :age")
    List<User> findOne(@Param("name") String name, @Param("age") Integer age);
}
