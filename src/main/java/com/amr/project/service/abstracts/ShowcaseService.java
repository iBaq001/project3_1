package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShowcaseService {

    Shop findById(Long id);

    List<Category> returnCategoryOfItemsInTheShop(Long shopId);

    void removeShop(Shop shop);

    List<ItemForShowcaseDto> itemsDtoOfTheShop(Long shopId);

    List<ShopDto> getAllShopDto();

    ShopDto getShopDtoById(Long id);

    void addShop(ShopDto shopDto);

    ShopDto getShopDtoByName(String name);

    void removeShopById(Long id);

    void updateShop(Long id, ShopDto shopDto);
}