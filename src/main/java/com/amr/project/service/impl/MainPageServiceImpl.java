package com.amr.project.service.impl;

import com.amr.project.converter.ItemToItemForShowcaseDtoConverter;
import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.MainPageService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MainPageServiceImpl implements MainPageService {

    private final ShopDao shopDao;
    private final ItemDao itemDao;

    public MainPageServiceImpl(ShopDao shopDao,
                               ItemDao itemDao) {
        this.shopDao = shopDao;
        this.itemDao = itemDao;
    }

    @Override
    public Set<ItemForShowcaseDto> getBestRatingItems() {
        return itemDao.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Item::getRating))
                .limit(12)
                .map(ItemToItemForShowcaseDtoConverter::convertItemToItemForShowcaseDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ShopDto> getBestRatingShops() {
        return shopDao.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Shop::getRating))
                .limit(12)
                .map(ShopToShopDtoConverter::convertShopToShopDto)
                .collect(Collectors.toSet());
    }
}
