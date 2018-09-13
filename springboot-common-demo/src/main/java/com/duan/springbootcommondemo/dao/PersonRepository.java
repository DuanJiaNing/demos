package com.duan.springbootcommondemo.dao;

import com.duan.springbootcommondemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created on 2018/9/12.
 *
 * @author DuanJiaNing
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findFirst10ByIdGreaterThan(Integer id);

    @Query("select id,name,age,address from Person where name = :name and age > :age")
    List<Person> findOne(@Param("name") String name, @Param("age") Integer age);
}
