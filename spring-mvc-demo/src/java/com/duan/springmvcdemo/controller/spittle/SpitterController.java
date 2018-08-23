package com.duan.springmvcdemo.controller.spittle;

import com.duan.springmvcdemo.entity.Spittle;
import com.duan.springmvcdemo.exceptions.DuplicateSpittleException;
import com.duan.springmvcdemo.exceptions.SpittleException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * Created on 2018/8/17.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/spittle")
public class SpitterController {

    private static final String dir = "C:/Users/ai/Desktop/";

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(@RequestPart("profilePicture") MultipartFile profilePicture, Spittle spittle,
                             RedirectAttributes model) throws IOException {

        String path = dir + profilePicture.getOriginalFilename();
        profilePicture.transferTo(new File(path));

        model.addAttribute("username", "Tom"); // 加载路径参数中
        model.addAttribute("avatar", path); // 普通字符串作为查询参数
        // 复杂对象通过临时保存在会话的 flash 属性中，在 redircet 后从会话中获取并自动注入到查询参数中
        model.addFlashAttribute("spittle", spittle);

        return "redirect:/spittle/{username}";
    }

    @GetMapping("/{username}")
    public String spittle(@PathVariable String username, Model model) {

        Spittle spittle = (Spittle) model.asMap().get("spittle");
        if (!model.containsAttribute("spittle")) {
            model.addAttribute("spittle", new Object()); // 不存在再查询
        }

        return "register";
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
