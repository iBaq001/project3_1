package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemDao extends ReadWriteDao<CartItem, Long> {

    List<CartItem> findCartItemByUserId(Long userId);

    Optional<CartItem> findCartItem(Long itemId, Long shopId, Long userId);

    int getQtyOfItemsInCart(Long itemId, Long shopId, Long userId);


}
