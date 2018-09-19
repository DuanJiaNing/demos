package com.duan.springbootcommondemo.service;

import com.duan.common.base.restful.ResultModel;
import com.duan.springbootcommondemo.dao.UserRoleRepository;
import com.duan.springbootcommondemo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository roleRepository;

    public ResultModel<Integer> addUserRole(UserRole role) {
        UserRole save = roleRepository.save(role);
        return new ResultModel<>(save.getId());
    }

}
