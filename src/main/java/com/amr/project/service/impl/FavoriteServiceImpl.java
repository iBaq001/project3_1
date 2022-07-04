package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.FavoriteDao;
import com.amr.project.mapper.FavoriteMapper;
import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.FavoriteService;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl extends ReadWriteServiceImpl<Favorite, Long> implements FavoriteService {

    private final FavoriteDao favoriteDao;
    private final FavoriteMapper favoriteMapper;
    private final ItemService itemService;
    private final ShopService shopService;

    @Autowired
    public FavoriteServiceImpl(FavoriteDao favoriteDao, FavoriteMapper favoriteMapper, ItemService itemService, ShopService shopService) {
        super(favoriteDao);
        this.favoriteDao = favoriteDao;
        this.favoriteMapper = favoriteMapper;
        this.itemService = itemService;
        this.shopService = shopService;
    }

    @Override
    public FavoriteDto getFavorite(Long userId) {
        return favoriteMapper.favoriteToFavoriteDto(favoriteDao.getFavorite(userId));
    }



    @Transactional
    @Override
    public void addFavoriteItemById(Long userId, Long itemId) {
        Favorite favorite = favoriteDao.getFavorite(userId);
        List<Item> items = favorite.getItems();
        Item item = itemService.getItemById(itemId);
        if (!(items.contains(item))) {
            items.add(item);
            favorite.setItems(items);
            favoriteDao.update(favorite);
        }
    }

    @Transactional
    @Override
    public void addFavoriteShopById(Long userId, Long shopId) {
        Favorite favorite = favoriteDao.getFavorite(userId);
        List<Shop> shops = favorite.getShops();
        Shop shop = shopService.getShopById(shopId);
        if (!(shops.contains(shop))) {
            shops.add(shop);
            favorite.setShops(shops);
            favoriteDao.update(favorite);
        }
    }

    @Transactional
    @Override
    public void removeFavoriteItemById(Long userId, Long itemId) {
        Favorite favorite = favoriteDao.getFavorite(userId);
        List<Item> items = favorite.getItems();
        Item item = itemService.getItemById(itemId);
        if (items.contains(item)) {
            items.remove(item);
            favorite.setItems(items);
            favoriteDao.update(favorite);
        }
    }

    @Transactional
    @Override
    public void removeFavoriteShopById(Long userId, Long shopId) {
        Favorite favorite = favoriteDao.getFavorite(userId);
        List<Shop> shops = favorite.getShops();
        Shop shop = shopService.getShopById(shopId);
        if (shops.contains(shop)) {
            shops.remove(shop);
            favorite.setShops(shops);
            favoriteDao.update(favorite);
        }
    }
}
