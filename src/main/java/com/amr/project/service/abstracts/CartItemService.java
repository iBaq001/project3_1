package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;

import java.util.List;

public interface CartItemService extends ReadWriteService<CartItem, Long> {

    void addItemToCart(Long itemId, Long shopId, int quantity);
    List<CartItemDto> getListOfCartItemDTOs();

    public void setUser(User loggedUser);

    public void updateQuantity(CartItemDto cartItemDto);

//    void updateQuantityInCart(Long id, int quantity);
    void deleteCartItem(Long cartItemId);

    String getCartItemName(Long itemId);
}
