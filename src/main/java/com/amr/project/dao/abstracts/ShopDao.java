package com.amr.project.dao.abstracts;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopDao {

    public Shop findById(Long id);

    void delete(Shop shop);

    List<Shop> findAll();

    void update(Shop shop);

    Shop findShopByName(String name);

    void save(Shop shop);

}