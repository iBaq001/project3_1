package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    private final UserService userService;


    public ShopController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showShops(Model model) {
        List<Shop> shopList = shopService.findAll();
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("shops", shopList);
        model.addAttribute("user", userService.findByUserName(user.getUsername()));
        return "admin_shops";
    }

    @GetMapping()
    public String getShops(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "1") int size,
            Model model) {
        Page<ShopDto> pages = shopService.findAll(PageRequest.of(page, size));
        model.addAttribute("page", pages);
        model.addAttribute("numbers", IntStream.range(0,pages.getTotalPages()).toArray());
        model.addAttribute("shops", pages.getContent());
        return "shops";
    }
}
