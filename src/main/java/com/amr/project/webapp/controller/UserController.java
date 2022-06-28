package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import com.amr.project.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

//@RestController
@Controller
public class UserController {
    private UserDetailsServiceImpl userService;

    @Autowired
    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "/admin";
    }

//контроллер для инфо о текущем юзере (можно доработать в личный кабинет)
    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.findUserByEmail(user.getEmail()));
        return "user";
    }
}
