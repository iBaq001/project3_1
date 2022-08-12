package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class ItemDaoImpl extends ReadWriteDaoImpl<Item, Long> implements ItemDao {

    @Override
    public List<Item> getItemsByShopId(Long shopId) {
        TypedQuery<Item> query = em.createQuery("select item from Item item where item.shop.id = :shopId", Item.class);
        return query.setParameter("shopId", shopId).getResultList();
    }

    @Override
    public List<Item> getItemsByName(String name) {
        return em.createQuery("select item from Item item where item.name like : like", Item.class)
                .setParameter("like", '%' + name + '%')
                .getResultList();
    }

    @Override
    public List<Item> getBestRatingItems(int limit) {
        return em.createQuery("SELECT item FROM Item item ORDER BY item.rating DESC", Item.class)
                .setMaxResults(limit)
                .getResultList();
    }
}
