package com.duan.springbootcommondemo;

import com.duan.springbootcommondemo.dao.PersonRepository;
import com.duan.springbootcommondemo.service.PersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 2018/9/13.
 *
 * @author DuanJiaNing
 */
public class Test2 extends BaseTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Test
    public void test() {
//        out(personRepository.findAll());
//        out(personRepository.findByName("Tom"));
//        out(personRepository.findFirst10ByIdGreaterThan(1));
        out(personRepository.findOne("Tom", 10));

    }

    @Test
    public void test1() {
//        outObj(personService.getPerson(3));
//        outObj(personService.getPersonIfNeed(3));
        outObj(personService.removePerson(3));
    }

    private <T> void out(List<T> list) {
        list.forEach(System.out::println);
    }

    private <T> void outObj(T obj) {
        System.out.println(obj);
    }

}
