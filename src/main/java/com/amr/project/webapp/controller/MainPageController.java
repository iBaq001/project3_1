package com.amr.project.webapp.controller;

import com.amr.project.service.abstracts.MainPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private final MainPageService mainPageService;

    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {

        final int LIMIT_OF_RETURNED_VALUES = 12;
        model.addAttribute("bestRatingShops", mainPageService.getBestRatingShops(LIMIT_OF_RETURNED_VALUES));
        model.addAttribute("bestRatingItems", mainPageService.getBestRatingItems(LIMIT_OF_RETURNED_VALUES));
        return "index";
    }
}
