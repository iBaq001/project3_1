package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ShopDaoImpl extends ReadWriteDaoImpl<Shop, Long> implements ShopDao {


    @Override
    public Shop findShopByName(String name) {
        TypedQuery<Shop> query = em.createQuery(
                "SELECT u FROM Shop u WHERE u.name = :name", Shop.class);
        Shop shop = query.setParameter("name", name)
                .getSingleResult();
        return shop;
    }

    @Override
    public List<Shop> getShopByName(String name) {
        return em.createQuery("select shop from Shop shop where shop.name like : like", Shop.class)
                .setParameter("like", '%' + name + '%')
                .getResultList();
    }
//
//    @Override
//    public void save(Shop shop) {
//        em.persist(shop);
//    }

    @Override
    public List<Shop> getBestRatingShops(int limit) {
        return em.createQuery("SELECT shop FROM Shop shop ORDER BY shop.rating DESC", Shop.class)
                .setMaxResults(limit)
                .getResultList();
    }


}