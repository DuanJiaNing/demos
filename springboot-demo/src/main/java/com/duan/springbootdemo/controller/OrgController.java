package com.duan.springbootdemo.controller;

import com.duan.springbootdemo.entity.Org;
import com.duan.springbootdemo.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String addOrg(@Valid Org org, BindingResult bResult) {
        if (bResult.hasErrors()) {
            System.out.println(bResult.getFieldError().getDefaultMessage());
        }

        Org save = orgRepository.save(org);
        return save.toString();
    }

    @GetMapping("/{id}")
    public Org getOrg(@PathVariable Integer id) {
        return orgRepository.findOne(id);
    }

    @PutMapping("/{id}")
    public Org updateOrg(@PathVariable Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("number") Integer number,
                         @RequestParam("intro") String intro) {
        Org org = new Org();
        org.setId(id);
        org.setIntro(intro);
        org.setName(name);
        org.setMobile(number);

        return orgRepository.save(org);
    }

    @DeleteMapping("/{id}")
    public void deleteOrg(@PathVariable Integer id) {
        orgRepository.delete(id);
    }

    @GetMapping("/number/{age}")
    public List<Org> getOrgByNumber(@PathVariable Integer age) {
        return orgRepository.findByAge(age);
    }

}
