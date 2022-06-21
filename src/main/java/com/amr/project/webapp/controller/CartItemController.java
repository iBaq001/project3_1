package com.amr.project.webapp.controller;

import com.amr.project.mapper.ItemMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/")
public class CartItemController {

    private ItemMapper itemMapper;
    private CartItemService cartItemService;
    private UserService userService;


    public CartItemController(ItemMapper itemMapper, CartItemService cartItemService, UserService userService) {
        this.itemMapper = itemMapper;
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @PostMapping("addItem")
    public void addItemToCart(@RequestParam Long itemId, @RequestParam Long shopId,
                              @RequestParam int quantity) {
        cartItemService.addItemToCart(itemId, shopId, quantity);
    }

    @GetMapping
    public List<CartItemDto> getCartItemDTOs() {
        return cartItemService.getListOfCartItemDTOs();
    }

    @PutMapping
    public void editQuantityInTheCart(@RequestBody List<CartItemDto> cartItemDtoList) {
        cartItemService.updateQuantityInCart(cartItemDtoList);
    }

    @DeleteMapping
    public void deleteCartItem(@RequestParam Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
    }

}
