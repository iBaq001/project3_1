package com.amr.project.service.abstracts;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;

import java.util.List;

public interface CartItemService extends ReadWriteService<CartItem, Long> {

    void addItemToCart(Long itemId, Long shopId, int quantity);

    List<CartItemDto> getListOfCartItemDTOs();

    public void setUser(User loggedUser);

    public void updateQuantityInCart(List<CartItemDto> cartItemDtoList);

    public void deleteCartItem(Long cartItemId);
}
