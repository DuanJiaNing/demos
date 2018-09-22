package com.duan.springclouddemo.person.controller;

import com.duan.springclouddemo.person.domain.Person;
import com.duan.springclouddemo.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/save")
    public List<Person> savePerson(String personName) {
        Person person = new Person();
        person.setName(personName);

        personRepository.save(person);

        Iterable<Person> all = personRepository.findAll();
        List<Person> list = new ArrayList<>();
        while (all.iterator().hasNext()) list.add(all.iterator().next());

        return list;

    }

}
