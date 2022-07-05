package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.FavoriteDao;
import com.amr.project.model.entity.Favorite;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteDaoImpl extends ReadWriteDaoImpl<Favorite, Long> implements FavoriteDao {

    @Override
    public Favorite getFavorite(Long userId) {
        return em.createQuery("select favorite from Favorite favorite where favorite.user.id = : like", Favorite.class)
                .setParameter("like", userId)
                .getSingleResult();
    }

    @Override
    public void removeFavoriteItemById(Long userId, Long itemId) {
        em.createNativeQuery("DELETE FROM favorite_item WHERE favorite_item.favorite_id = ( SELECT id FROM favorite WHERE favorite.user_id = :userId) AND favorite_item.item_id = :itemId")
                .setParameter("userId", userId)
                .setParameter("itemId", itemId)
                .executeUpdate();
    }

    @Override
    public void removeFavoriteShopById(Long userId, Long shopId) {
        em.createNativeQuery("DELETE FROM favorite_shop WHERE favorite_shop.favorite_id = (SELECT id FROM favorite WHERE favorite.user_id = :userId) AND favorite_shop.shop_id = :shopId")
                .setParameter("userId", userId)
                .setParameter("shopId", shopId)
                .executeUpdate();
    }

    @Override
    public void addFavoriteItemById(Long userId, Long itemId) {
        em.createNativeQuery("INSERT INTO favorite_item VALUES ((SELECT id FROM favorite WHERE favorite.user_id = :userId LIMIT 1), :itemId)")
                .setParameter("userId", userId)
                .setParameter("itemId", itemId)
                .executeUpdate();
    }

    @Override
    public void addFavoriteShopById(Long userId, Long shopId) {
        em.createNativeQuery("INSERT INTO favorite_shop VALUES ((SELECT id FROM favorite WHERE favorite.user_id = :userId LIMIT 1), :shopId)")
                .setParameter("userId", userId)
                .setParameter("shopId", shopId)
                .executeUpdate();
    }
}
