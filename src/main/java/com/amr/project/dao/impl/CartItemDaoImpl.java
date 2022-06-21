package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.model.entity.CartItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CartItemDaoImpl extends ReadWriteDaoImpl<CartItem, Long> implements CartItemDao {


    @Override
    public List<CartItem> findCartItemByUserId(Long userId) {
        TypedQuery<CartItem> query = em.createQuery(
                "select cart from CartItem cart where cart.user.id = :userId", CartItem.class);
        return query.setParameter("userId", userId).getResultList();


    }

    @Override
    public Optional<CartItem> findCartItem(Long itemId, Long shopId, Long userId) {
        TypedQuery<CartItem> query = em.createQuery(
                "select cart from CartItem cart where cart.itemInCart.id = :itemId and " +
                        "cart.user.id = :userId and cart.shop.id = :shopId", CartItem.class);
        List<CartItem> result = query
                .setParameter("itemId", itemId)
                .setParameter("userId", userId)
                .setParameter("shopId", shopId)
                .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public int getQtyOfItemsInCart(Long itemId, Long shopId, Long userId) {
        TypedQuery<Integer> query = em.createQuery(
                "select SUM(cart.quantity) from CartItem cart where cart.itemInCart.id = :itemId and " +
                        "cart.user.id = :userId and cart.shop.id = :shopId", Integer.class);
        return query
                .setParameter("itemId", itemId)
                .setParameter("userId", userId)
                .setParameter("shopId", shopId)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void update(CartItem entity) {
        em.merge(entity);
    }

}
