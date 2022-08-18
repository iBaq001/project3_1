package com.amr.project.webapp.controller;

import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@PreAuthorize("isFullyAuthenticated()")
@RestController
public class FavoriteRestController {

    FavoriteService favoriteService;

    public FavoriteRestController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/favorite/")
    public ModelAndView getFavoriteDto(ModelAndView modelAndView, @AuthenticationPrincipal User loggedUser) {
        FavoriteDto favorite = favoriteService.getFavorite(loggedUser.getId());
        modelAndView.addObject("favorite", favorite);
        modelAndView.setViewName("favorite");
        return modelAndView;
    }

    @DeleteMapping ("/favorite/shop/remove/")
    public ResponseEntity<String> removeFavoriteShopDtoById(@AuthenticationPrincipal User loggedUser, HttpServletRequest request) {
        String shopId = request.getParameter("shopId");
        try {
            favoriteService.removeFavoriteShopById(loggedUser.getId(), Long.valueOf(shopId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"INTERNAL_SERVER_ERROR\"");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\":1}");
    }

    @DeleteMapping ("/favorite/item/remove/")
    public ResponseEntity<String> removeFavoriteItemDtoById(@AuthenticationPrincipal User loggedUser, HttpServletRequest request) {
        String itemId = request.getParameter("itemId");
        try {
            favoriteService.removeFavoriteItemById(loggedUser.getId(), Long.valueOf(itemId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"INTERNAL_SERVER_ERROR\"");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\":1}");
    }

    @PostMapping ("/favorite/shop/add/")
    public ResponseEntity<String> addFavoriteShopDtoById(@AuthenticationPrincipal User loggedUser, HttpServletRequest request) {
        String shopId = request.getParameter("shopId");
        try {
            favoriteService.addFavoriteShopById(loggedUser.getId(), Long.valueOf(shopId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"INTERNAL_SERVER_ERROR\"");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\":1}");
    }

    @PostMapping ("/favorite/item/add/")
    public ResponseEntity<String> addFavoriteItemDtoById(@AuthenticationPrincipal User loggedUser, HttpServletRequest request) {
        String itemId = request.getParameter("itemId");
        try {
            favoriteService.addFavoriteItemById(loggedUser.getId(), Long.valueOf(itemId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"INTERNAL_SERVER_ERROR\"");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"success\":1}");
    }



}
