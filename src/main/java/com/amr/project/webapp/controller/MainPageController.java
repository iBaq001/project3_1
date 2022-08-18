package com.amr.project.webapp.controller;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.FavoriteService;
import com.amr.project.service.abstracts.MainPageService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainPageController {

    private final MainPageService mainPageService;
    private final FavoriteService favoriteService;

    private final CartItemService cartItemService;

    public MainPageController(MainPageService mainPageService, FavoriteService favoriteService, CartItemService cartItemService) {
        this.mainPageService = mainPageService;
        this.favoriteService = favoriteService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("/")
    public String getMainPage(Model model, @AuthenticationPrincipal User loggedUser) {

        if (loggedUser != null) {
            model.addAttribute("favorite", favoriteService.getFavorite(loggedUser.getId()));
        } else {
            model.addAttribute("favorite", null);
        }
        model.addAttribute("bestRatingShops", mainPageService.getBestRatingShops(12));
        model.addAttribute("bestRatingItems", mainPageService.getBestRatingItems(12));
        return "index";
    }
}
