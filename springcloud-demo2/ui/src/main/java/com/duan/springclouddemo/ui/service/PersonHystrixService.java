package com.duan.springclouddemo.ui.service;

import com.duan.springclouddemo.ui.domain.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@Service
public class PersonHystrixService {

    @Autowired
    private PersonService personService;

    @HystrixCommand(fallbackMethod = "fallbackSave") // save 方法调用失败时调用后备方法 fallbackSave
    public List<Person> save(String name) {
        return personService.save(name);
    }

    // 除了方法名，方法签名要相同
    public List<Person> fallbackSave(String name) {
        List<Person> ps = new ArrayList<>();
        Person p = new Person();
        p.setName("HystrixCommand fallbackMethod");
        ps.add(p);

        return ps;
    }

}
