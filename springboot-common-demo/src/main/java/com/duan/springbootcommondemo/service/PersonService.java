package com.duan.springbootcommondemo.service;

import com.duan.springbootcommondemo.dao.PersonRepository;
import com.duan.springbootcommondemo.entity.Person;
import com.duan.springbootcommondemo.rest.ResultModel;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2018/9/17.
 *
 * @author DuanJiaNing
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public ResultModel<Integer> insertPerson(Person person) {

        Person save = personRepository.save(person);

        if (StringUtils.isEmptyOrWhitespaceOnly(person.getName())) {
            throw new IllegalArgumentException();
        }

        if (save != null && save.getId() != null) {
            return new ResultModel<>(save.getId());
        }

        return ResultModel.fail();
    }

    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public void testPerson(Person person) {

        Person save = personRepository.save(person);

        if (StringUtils.isEmptyOrWhitespaceOnly(person.getName())) {
            throw new IllegalArgumentException();
        }

    }

    // 无论如何方法都会调用，添加 person 到缓存，key 为参数 id
    @CachePut(value = "person", key = "#id")
    public Person getPerson(Integer id) {
        System.out.println("getPerson for cache test");
        return personRepository.findOne(id);
    }

    // 删除缓存 person，key 为参数 id
    @CacheEvict(value = "person", key = "#id")
    public void removePerson(Integer id) {
        System.out.println("removePerson for cache test");
        personRepository.delete(id);
    }

    // 缓存中存在时直接返回，不调用方法，否则调用方法并放到缓存中
    @Cacheable(value = "person", key = "#id")
    public Person getPersonIfNeed(Integer id) {
        System.out.println("getPersonIfNeed for cache test");
        return personRepository.findOne(id);
    }

}
