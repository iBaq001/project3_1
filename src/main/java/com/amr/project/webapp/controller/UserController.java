package com.amr.project.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@RestController
@Controller
public class UserController {
    @GetMapping("/admin")
    public String adminPage() {
        return "/admin";
    }
}