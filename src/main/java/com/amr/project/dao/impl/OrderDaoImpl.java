package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.OrderDao;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class OrderDaoImpl extends ReadWriteDaoImpl<Order, Long> implements OrderDao {
    @Override
    @Transactional
    public List<Item> itemsListByShopId(Long shopId){
        TypedQuery<Item> query = em.createQuery("select item from Item item where item.shop.id = :shopId", Item.class);
        return query.setParameter("shopId", shopId).getResultList();
    }
}
