package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ItemDaoImpl extends ReadWriteDaoImpl<Item, Long> implements ItemDao {

    @Override
    public List<Item> getItemsByShopId(Long shopId) {
        TypedQuery<Item> query = em.createQuery("select item from Item item where item.shop.id = :shopId", Item.class);
        return query.setParameter("shopId", shopId).getResultList();
    }
}
