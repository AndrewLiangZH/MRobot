package com.team.mrobot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.team.mrobot.web.domain.User;
import com.team.mrobot.web.service.UserService;

/**
 * Project: TaaS-test
 * Author: AndrewLiang
 * Date: 2017/9/28
 * Description:
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        User user = userService.findByUsername(username);
        model.addAttribute("username", user.getUsername());
        System.out.println(user.getUsername());
        if (user.getPassword().equals(password)) {
            return new ModelAndView("redirect:/backstage/"+username);
        } else {
            model.addAttribute("msg", "Username or password is wrong!");
            return new ModelAndView("/login", "msgModel", model);
        }
    }

    @PostMapping("/register")
    public ModelAndView register(User user){
        userService.saveUser(user);
        return new ModelAndView("/login");
    }

}
