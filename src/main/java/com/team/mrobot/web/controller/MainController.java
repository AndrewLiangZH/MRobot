package com.team.mrobot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import com.team.mrobot.web.vo.Menu;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/backstage/{username}")
    public ModelAndView listTModels(@PathVariable("username")String username, Model model) {
        List<Menu> list = new ArrayList<>();
//        list.add(new Menu("Overview", "/overview"));
        System.out.println(username);
        list.add(new Menu("TModel Management", "/tmodel"));
        list.add(new Menu("eDoctor Management","/eDoctor"));
        list.add(new Menu("User Management", "/user"));
        model.addAttribute("list", list);
        model.addAttribute("username",username);
        return new ModelAndView("backstage/index", "menuList", model);
    }


}
