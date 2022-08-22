package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/shops")
public class ShopController {
    private final ShopService shopService;

    private final UserService userService;


    public ShopController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping
    public String showShops(Model model) {
        List<Shop> shopList = shopService.findAll();
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("shops", shopList);
        model.addAttribute("user", userService.findByUserName(user.getUsername()));
        return "admin_shops";
    }
}
