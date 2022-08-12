package com.amr.project.service.impl;

import com.amr.project.converter.ItemToItemForShowcaseDtoConverter;
import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.MainPageService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<ItemForShowcaseDto> getBestRatingItems(int limit) {
        return itemDao.getBestRatingItems(limit)
                .stream()
                .map(ItemToItemForShowcaseDtoConverter::convertItemToItemForShowcaseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopDto> getBestRatingShops(int limit) {
        return shopDao.getBestRatingShops(limit)
                .stream()
                .map(ShopToShopDtoConverter::convertShopToShopDto)
                .collect(Collectors.toList());
    }
}
