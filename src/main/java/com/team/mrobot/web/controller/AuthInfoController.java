package com.team.mrobot.web.controller;

import com.team.mrobot.web.domain.AuthInfo;
import com.team.mrobot.web.service.AuthInfoService;
import com.team.mrobot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthInfoController {

    @Autowired
    private AuthInfoService authInfoService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ModelAndView auth(AuthInfo authInfo){

        authInfoService.save(authInfo);

        userService.updateType(1,authInfo.getAccount());//

        return new ModelAndView("/auth");
    }

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }
}
