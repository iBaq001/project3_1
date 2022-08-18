package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import com.amr.project.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

//@RestController
@Controller
public class UserController {
    private UserDetailsServiceImpl userDetailsService;
    private UserService userService;


    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", userDetailsService.findUserByUsername(user.getUsername()));

//        model.addAttribute("user", user);
        return "/admin";
    }

    //контроллер для инфо о текущем юзере (можно доработать в личный кабинет)
    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userDetailsService.findUserByUsername(user.getUsername()));
        return "/user";
    }
}
