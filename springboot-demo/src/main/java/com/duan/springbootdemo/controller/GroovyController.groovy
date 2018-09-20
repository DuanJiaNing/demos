package com.duan.springbootdemo.controller

import com.duan.springbootdemo.domain.Org
import com.duan.springbootdemo.domain.Result
import com.duan.springbootdemo.repository.OrgRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created on 2018/9/19.
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/groovy")
class GroovyController {

    @Autowired
    private OrgRepository orgRepository

    @GetMapping("/orgList")
    def orgList() {
        def orgs = orgRepository.findAll()
        def result = new Result<List<Org>>()
        def checkRepl = { org -> if (org.age == null || org.age < 0) org.age = 100 else org.age = -100 }

        orgs.each { org -> checkRepl(org) }

        result.code = 1
        result.msg = "成功"
        result.data = orgs

        return result
    }


}
