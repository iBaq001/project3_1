package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShowcaseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ShowcaseRestController {

    ShowcaseService showcaseService;
    ShopDto shopDto;

    public ShowcaseRestController(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
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
    public void deleteUser(@PathVariable("id") Long id) {
        showcaseService.removeShopById(id);
    }

    @GetMapping("/shop/id/{shopId}")
    public ModelAndView showcase(@PathVariable Long shopId) {
        Shop shop = showcaseService.findById(shopId);
        shopDto = ShopToShopDtoConverter.convertShopToShopDto(shop);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showcase");
        modelAndView.addObject("shopDto", shopDto);
        modelAndView.addObject("itemsCategoriesInTheShop", showcaseService.returnCategoryOfItemsInTheShop(shopId));
        modelAndView.addObject("itemsOfTheShop", showcaseService.itemsDtoOfTheShop(shopId));
        return modelAndView;
    }

}
