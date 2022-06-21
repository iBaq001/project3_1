package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.ShowcaseService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ShowcaseRestController {

    private ShowcaseService showcaseService;
    private ShopDto shopDto;

    private CartItemService cartItemService;

    public ShowcaseRestController(ShowcaseService showcaseService, CartItemService cartItemService) {
        this.showcaseService = showcaseService;
        this.cartItemService = cartItemService;
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
    public ShopDto addShop(@RequestBody Shop shop){
        showcaseService.addShop(shop);
        return showcaseService.getShopDtoByName(shop.getName());
    }

    @DeleteMapping("/shop/{id}")
    public void deleteShop(@PathVariable("id") Long id) {
        showcaseService.removeShopById(id);
    }

    @GetMapping("/shop/id/{shopId}")
    public ModelAndView showcase(@PathVariable Long shopId, @AuthenticationPrincipal User loggedUser) {
        Shop shop = showcaseService.findById(shopId);
        shopDto = ShopToShopDtoConverter.convertShopToShopDto(shop);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showcase");
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
