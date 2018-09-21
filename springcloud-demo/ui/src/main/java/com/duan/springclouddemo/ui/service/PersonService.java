package com.duan.springclouddemo.ui.service;

import com.duan.springclouddemo.ui.domain.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 2018/9/21.
 *
 * @author DuanJiaNing
 */
@FeignClient("person")
public interface PersonService {

    @RequestMapping(value = "/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    List<Person> save(@RequestBody String name);
}
