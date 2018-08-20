package com.duan.springmvcdemo.controller;

import com.duan.springmvcdemo.entity.Spittle;
import com.duan.springmvcdemo.exceptions.DuplicateSpittleException;
import com.duan.springmvcdemo.exceptions.SpittleException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * Created on 2018/8/17.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private static final String dir = "C:/Users/ai/Desktop/";

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/doRegister")
    public ModelAndView doRegister(@RequestPart("profilePicture") MultipartFile profilePicture) {

        ModelAndView mv = new ModelAndView("register");
        try {
            String path = dir + profilePicture.getOriginalFilename();
            profilePicture.transferTo(new File(path));
            mv.addObject("spitterAvatar", path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mv;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Spittle find(@PathVariable long id) {
        if (id <= 0) {
            throw new SpittleException(); // @ResponseStatus 将转为 404 的返回码
        }

        if (id == 1) {
            throw new DuplicateSpittleException();
        }

        return new Spittle("haha", new Date());
    }

}
