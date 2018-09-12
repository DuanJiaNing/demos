package com.duan.springbootcommondemo.dto;

import com.duan.springbootcommondemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2018/9/12.
 *
 * @author DuanJiaNing
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
