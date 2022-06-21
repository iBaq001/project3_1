package com.amr.project.dao.abstracts;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopDao extends ReadWriteDao<Shop, Long> {

    public Shop findById(Long id);

    void delete(Shop shop);

    List<Shop> findAll();

    void update(Shop shop);

    Shop findShopByName(String name);

    List<Shop> getShopByName(String name);

    List<Shop> getBestRatingShops(int limit);
}