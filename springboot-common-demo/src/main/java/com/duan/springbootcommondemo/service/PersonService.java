package com.duan.springbootcommondemo.service;

import com.duan.springbootcommondemo.dao.PersonRepository;
import com.duan.springbootcommondemo.entity.Person;
import com.duan.springbootcommondemo.rest.ResultModel;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

}
