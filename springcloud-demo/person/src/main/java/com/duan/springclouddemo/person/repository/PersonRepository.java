package com.duan.springclouddemo.person.repository;

import com.duan.springclouddemo.person.domain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
