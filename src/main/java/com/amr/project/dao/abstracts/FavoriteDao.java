package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Favorite;

public interface FavoriteDao extends ReadWriteDao<Favorite, Long> {

    Favorite getFavorite(Long userId);

    void removeFavoriteItemById(Long userId, Long itemId);

    void removeFavoriteShopById(Long userId, Long shopId);

    void addFavoriteItemById(Long userId, Long itemId);

    void addFavoriteShopById(Long userId, Long shopId);

}