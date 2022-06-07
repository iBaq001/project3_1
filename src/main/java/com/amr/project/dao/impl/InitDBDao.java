package com.amr.project.dao.impl;

import com.amr.project.model.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class InitDBDao {
    @PersistenceContext
    protected EntityManager em;

    public InitDBDao() {}

    @Transactional
    public void persist(Shop shop) {
        em.persist(shop);
    }

    @Transactional
    public void persist(Review review) {
        em.persist(review);
    }

    @Transactional
    public void persist(Category category) {
        em.persist(category);
    }

    @Transactional
    public void persist(Item item) {
        em.persist(item);
    }

    @Transactional
    public void persist(Order order) {
        em.persist(order);
    }

    @Transactional
    public void persist(Image image) {
        em.persist(image);
    }

    public List<Item> getItemsByShopId(Long shopId) {
        TypedQuery<Item> query = em.createQuery("select item from Item item where item.shop.id = :shopId", Item.class);
        return query.setParameter("shopId", shopId).getResultList();
    }
}
