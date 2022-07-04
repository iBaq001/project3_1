package com.amr.project.service.abstracts;

import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;

public interface FavoriteService extends ReadWriteService<Favorite, Long> {
    FavoriteDto getFavorite(Long userId);
    void removeFavoriteItemById(Long userId, Long itemId);
    void removeFavoriteShopById(Long userId, Long shopId);
    void addFavoriteItemById(Long userId, Long itemId);
    void addFavoriteShopById(Long userId, Long shopId);
}
