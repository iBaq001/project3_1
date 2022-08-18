package com.amr.project.mapper;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {ShopMapper.class, ItemMapper.class, UserMapper.class})
public interface CartItemMapper {

    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "itemId", source = "itemInCart.id")
    @Mapping(target = "name", source = "itemInCart.name")
    @Mapping(target = "price", source = "itemInCart.price")
    @Mapping(target = "userId", source = "user.id")
    CartItemDto cartItemToCartItemDto(CartItem cartItem);

    @Mapping(target = "shop.id", source = "shopId")
    @Mapping(target = "itemInCart.id", source = "itemId")
    @Mapping(target = "user.id", source = "userId")
    CartItem cartItemDtoToCartItem(CartItemDto cartItemDto);

}