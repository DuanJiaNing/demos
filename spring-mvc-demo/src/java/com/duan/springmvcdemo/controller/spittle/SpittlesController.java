package com.duan.springmvcdemo.controller.spittle;

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

//    @Autowired
//    private SpittlesRepository spittlesRepository;

    @RequestMapping(method = GET)
    public String spittles(Model model) {
//        model.addAttribute(spittlesRepository.findSpittles(Long.MAX_VALUE, 20));

        return "spittles";
    }

}
