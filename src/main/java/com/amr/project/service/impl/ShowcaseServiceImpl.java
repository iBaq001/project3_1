package com.amr.project.service.impl;

import com.amr.project.converter.ItemToItemForShowcaseDtoConverter;
import com.amr.project.converter.ShopToShopDtoConverter;
import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShowcaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShowcaseServiceImpl implements ShowcaseService {
    ItemDao itemDao;
    ShopDao shopDao;

    public ShowcaseServiceImpl() {
    }

    @Override
    @Transactional
    public Shop findById(Long id) {
        return shopDao.findById(id);
    }

    @Override
    @Transactional
    public void removeShop(Shop shop) {
        shopDao.delete(shop);
    }

    @Override
    public List<Category> returnCategoryOfItemsInTheShop(Long shopId) {
        return findById(shopId).getItems().stream()
                .map(item -> item.getCategory())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemForShowcaseDto> itemsDtoOfTheShop(Long shopId) {
        return itemDao.getItemsByShopId(shopId)
                .stream().map(item -> ItemToItemForShowcaseDtoConverter.convertItemToItemForShowcaseDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopDto> getAllShopDto() {
        return shopDao.findAll().stream()
                .map(shop -> ShopToShopDtoConverter.convertShopToShopDto(shop))
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto getShopDtoById(Long id) {
        return ShopToShopDtoConverter.convertShopToShopDto(shopDao.findById(id));
    }

    @Override
    public void addShop(Shop shop) {
        shopDao.update(shop);
    }

    @Override
    public ShopDto getShopDtoByName(String name) {
        return ShopToShopDtoConverter.convertShopToShopDto(shopDao.findShopByName(name));
    }

    @Override
    public void removeShopById(Long id) {
        shopDao.delete(findById(id));
    }


}
