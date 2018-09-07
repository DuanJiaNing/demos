package com.duan.springmvcdemo.spittle.controller;

import com.duan.springmvcdemo.spittle.dao.SpittlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created on 2018/8/15.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/spittles")
public class SpittlesController {

    @Autowired
    private SpittlesRepository spittlesRepository;

    @RequestMapping(method = GET)
    public String spittles(Model model) {
        model.addAttribute(spittlesRepository.findSpittles());
        return "spittles";
    }

}
