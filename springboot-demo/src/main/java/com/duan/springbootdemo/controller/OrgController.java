package com.duan.springbootdemo.controller;

import com.duan.springbootdemo.entity.Org;
import com.duan.springbootdemo.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2018/6/6.
 *
 * @author DuanJiaNing
 */
@RestController()
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private OrgRepository orgRepository;

    @GetMapping("/list")
    public List<Org> orgList() {
        return orgRepository.findAll();
    }

    @PostMapping
    public String addOrg(@RequestBody Org org){
        Org save = orgRepository.save(org);
        return save.toString();
    }

}
