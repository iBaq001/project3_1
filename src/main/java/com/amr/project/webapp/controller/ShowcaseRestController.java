package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.FavoriteService;
import com.amr.project.service.abstracts.ShowcaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ShowcaseRestController {

    private ShowcaseService showcaseService;
    private FavoriteService favoriteService;
    private ShopDto shopDto;

    private CartItemService cartItemService;

    public ShowcaseRestController(ShowcaseService showcaseService, CartItemService cartItemService, FavoriteService favoriteService) {
        this.showcaseService = showcaseService;
        this.cartItemService = cartItemService;
        this.favoriteService = favoriteService;
    }

    @GetMapping("/shop/all")
    public List<ShopDto> getAllShopDto() {
        return showcaseService.getAllShopDto();
    }

    @GetMapping("/shop/{id}")
    public ShopDto getShopDtoById(@PathVariable Long id) {
        return showcaseService.getShopDtoById(id);
    }

    @PostMapping("/shop/{id}")
    public ResponseEntity<Void> addShop(@RequestBody ShopDto shopDto){
        showcaseService.addShop(shopDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/shop/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable("id") Long id) {
        showcaseService.removeShopById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/shop/{id}")
    public ResponseEntity<Void> updateShop(@PathVariable Long id, @RequestBody ShopDto shopDto) {
        showcaseService.updateShop(id, shopDto);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/shop/id/{shopId}")
    public ModelAndView showcase(@PathVariable Long shopId, @AuthenticationPrincipal User loggedUser) {
        Shop shop = showcaseService.findById(shopId);
        shopDto = ShopToShopDtoConverter.convertShopToShopDto(shop);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showcase");
        if (loggedUser != null) {
            modelAndView.addObject("favorite", favoriteService.getFavorite(loggedUser.getId()));
        } else {
            modelAndView.addObject("favorite", null);
        }
        modelAndView.addObject("shopDto", shopDto);
        modelAndView.addObject("itemsCategoriesInTheShop", showcaseService.returnCategoryOfItemsInTheShop(shopId));
        modelAndView.addObject("itemsOfTheShop", showcaseService.itemsDtoOfTheShop(shopId));
        cartItemService.setUser(loggedUser);
        return modelAndView;
    }

    @GetMapping("/shop/id/{shopId}/about")
    public ModelAndView about(@PathVariable Long shopId) {
        Shop shop = showcaseService.findById(shopId);
        shopDto = ShopToShopDtoConverter.convertShopToShopDto(shop);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop_info");
        modelAndView.addObject("shopDto", shopDto);
        modelAndView.addObject("itemsCategoriesInTheShop", showcaseService.returnCategoryOfItemsInTheShop(shopId));
        modelAndView.addObject("itemsOfTheShop", showcaseService.itemsDtoOfTheShop(shopId));
        return modelAndView;
    }

}
