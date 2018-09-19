package com.duan.springbootcommondemo.controller;

import com.duan.common.base.restful.ResultModel;
import com.duan.common.spring.verify.VerifyRule;
import com.duan.common.spring.verify.VerifyValueRule;
import com.duan.common.spring.verify.annoation.ParamVerifyComposite;
import com.duan.common.spring.verify.annoation.method.RequestParamValueVerify;
import com.duan.common.spring.verify.annoation.method.RequestParamVerify;
import com.duan.common.spring.verify.annoation.method.RequestParamsVerifyComposite;
import com.duan.springbootcommondemo.entity.User;
import com.duan.springbootcommondemo.entity.UserRole;
import com.duan.springbootcommondemo.service.UserRoleService;
import com.duan.springbootcommondemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService roleService;

    @GetMapping("/userPage")
    public ResultModel<Page<User>> userList() {
        return new ResultModel<>(userService.findList(1, 10));
    }

    @PostMapping("/user")
    @RequestParamsVerifyComposite({
            @ParamVerifyComposite(valueVerify = @RequestParamValueVerify(param = "password", rule = VerifyValueRule.TEXT_LENGTH_GREATER_THAN, value = "5")),
            @ParamVerifyComposite(valueVerify = @RequestParamValueVerify(param = "password", rule = VerifyValueRule.TEXT_LENGTH_LESS_THAN, value = "8")),
            @ParamVerifyComposite(@RequestParamVerify(param = "name", rule = VerifyRule.NOT_BLANK)),
            @ParamVerifyComposite(valueVerify = @RequestParamValueVerify(param = "age", rule = VerifyValueRule.VALUE_GREATER_THAN, value = "18"))
    })
    public ResultModel<Integer> addUser(
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam Integer age) {

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setAge(age);

        return userService.insertPerson(user);
    }

    @PostMapping("/role")
    public ResultModel<Integer> addUserRole(@RequestParam Integer uId,
                                            @RequestParam Integer rId) {
        UserRole role = new UserRole();
        role.setUId(uId);
        role.setRId(rId);

        return roleService.addUserRole(role);
    }


}
