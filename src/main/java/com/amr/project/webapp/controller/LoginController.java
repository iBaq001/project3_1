package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("newUser")User user, Model model) {
        model.addAttribute("newUser",user);
        return "login";
    }

}






