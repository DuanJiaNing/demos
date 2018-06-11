package com.duan.springbootdemo.service;

import com.duan.springbootdemo.domain.Org;
import com.duan.springbootdemo.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2018/6/7.
 *
 * @author DuanJiaNing
 */
@Service
public class OrgService {

    @Autowired
    private OrgRepository orgRepository;

    @Transactional
    public void test(){

        Org org = new Org();
        org.setId(2);
        org.setName("Jack");
        orgRepository.save(org);

        orgRepository.delete(1);
    }

}
