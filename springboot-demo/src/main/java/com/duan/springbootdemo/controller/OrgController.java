package com.duan.springbootdemo.controller;

import com.duan.springbootdemo.domain.Org;
import com.duan.springbootdemo.domain.Result;
import com.duan.springbootdemo.repository.OrgRepository;
import com.duan.springbootdemo.verify.VerifyRule;
import com.duan.springbootdemo.verify.VerifyValueRule;
import com.duan.springbootdemo.verify.annoation.ParamVerifyComposite;
import com.duan.springbootdemo.verify.annoation.method.RequestParamValueVerify;
import com.duan.springbootdemo.verify.annoation.method.RequestParamVerify;
import com.duan.springbootdemo.verify.annoation.method.RequestParamsVerifyComposite;
import com.duan.springbootdemo.verify.annoation.parameter.ParamValueVerify;
import com.duan.springbootdemo.verify.annoation.parameter.ParamVerify;
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
    public Result<List<Org>> orgList() {
        final List<Org> orgs = orgRepository.findAll();
        Result<List<Org>> result = new Result<>();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(orgs);

        return result;
    }

    @PostMapping
    public String addOrg(@Valid Org org, BindingResult bResult) {
        if (bResult.hasErrors()) {
            System.out.println(bResult.getFieldError().getDefaultMessage());
        }

        Org save = orgRepository.save(org);
        return save.toString();
    }

//    @RequestParamValueVerify(param = "name", rule = VerifyValueRule.EQUAL, value = "Tom")
//    @RequestParamValueVerify(param = "name", rule = VerifyValueRule.TEXT_LENGTH_LESS_THAN, value = "5")
//    @RequestParamVerify(param = "name", rule = VerifyRule.NOT_BLANK)

//    @RequestParamsVerify({
//            @RequestParamVerify(param = "name", rule = VerifyRule.NOT_BLANK),
//            @RequestParamVerify(param = "age", rule = VerifyRule.NON_NEGATIVE),
//            @RequestParamVerify(param = "email", rule = VerifyRule.NOT_BLANK)})

//    @RequestParamsValueVerify({
//            @RequestParamValueVerify(param = "name", rule = VerifyValueRule.TEXT_LENGTH_EQUAL, value = "3"),
//            @RequestParamValueVerify(param = "age", rule = VerifyValueRule.VALUE_GREATER_THAN, value = "20"),
//            @RequestParamValueVerify(param = "email", rule = VerifyValueRule.TEXT_REGEX, value = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")})

    @RequestParamsVerifyComposite({
            @ParamVerifyComposite(valueVerify = @RequestParamValueVerify(param = "name", rule = VerifyValueRule.TEXT_LENGTH_EQUAL, value = "3")),
            @ParamVerifyComposite(@RequestParamVerify(param = "email", rule = VerifyRule.NOT_BLANK)),
            @ParamVerifyComposite(valueVerify = @RequestParamValueVerify(param = "email", rule = VerifyValueRule.TEXT_REGEX, value = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")),
    })

    @GetMapping("/{id}")
    public Org getOrg(@PathVariable Integer id,
                      @RequestParam String name,
                      @RequestParam Integer age,
                      @RequestParam String email) {
        return orgRepository.findOne(id);
    }

    @PutMapping("/{id}")
//    @RequestParamVerify(param = "name", rule = VerifyRule.NOT_BLANK)
    public Org updateOrg(
            @PathVariable
            @ParamVerify(rule = VerifyRule.NON_NEGATIVE) Integer id,
            @RequestParam
            @ParamVerify(rule = VerifyRule.NOT_BLANK) String name,
            @RequestParam
            @ParamValueVerify(rule = VerifyValueRule.TEXT_REGEX, value = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$") String email,
            @RequestParam
            @ParamValueVerify("12") Integer number,
            @RequestParam
            @ParamValueVerify(rule = VerifyValueRule.TEXT_LENGTH_NOT_GREATER_THAN, value = "4") String intro) {
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
