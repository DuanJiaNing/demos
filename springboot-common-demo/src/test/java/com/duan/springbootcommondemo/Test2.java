package com.duan.springbootcommondemo;

import com.duan.springbootcommondemo.dao.UserRepository;
import com.duan.springbootcommondemo.service.UserService;
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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
//        out(userRepository.findAll());
//        out(userRepository.findByName("Tom"));
//        out(userRepository.findFirst10ByIdGreaterThan(1));
        out(userRepository.findOne("Tom", 10));

    }

    @Test
    public void test1() {
//        outObj(personService.testGetPerson(3));
//        outObj(personService.testGetPersonIfNeed(3));
        userService.testRemovePerson(3);
    }

    private <T> void out(List<T> list) {
        list.forEach(System.out::println);
    }

    private <T> void outObj(T obj) {
        System.out.println(obj);
    }

}
