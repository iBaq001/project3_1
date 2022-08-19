package com.amr.project.webapp.controller;

import com.amr.project.mapper.UserMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.ItemService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartItemController {

    private CartItemService cartItemService;

    private ItemService itemService;

    private UserMapper userMapper;

    public CartItemController(CartItemService cartItemService, ItemService itemService, UserMapper userMapper) {
        this.cartItemService = cartItemService;
        this.itemService = itemService;

        this.userMapper = userMapper;
    }

    @PostMapping("/cart/addItem/{id}")
    public ModelAndView addItemToCart(@PathVariable("id") Long itemId, @RequestParam(defaultValue = "1") Long shopId,
                                      @RequestParam(defaultValue = "1") int quantity, ModelAndView modelAndView) {
        List<CartItemDto> cartItemDtoList = cartItemService.getListOfCartItemDTOs();
        cartItemService.addItemToCart(itemId, shopId, quantity);
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("cart", cartItemDtoList);
        modelAndView.addObject("item", itemService.getItemById(itemId));
        return modelAndView;
    }

    @GetMapping("/list")
    public List<CartItemDto> getCartItemDTOs() {
        return cartItemService.getListOfCartItemDTOs();
    }

    @GetMapping("/order")
    public ModelAndView getOrder(ModelAndView modelAndView) {
        List<CartItemDto> cartItemDtoList = cartItemService.getListOfCartItemDTOs();
        modelAndView.addObject("itemsInCart", cartItemDtoList);
        modelAndView.setViewName("ordering");
        Long sum = 0L;
        for (CartItemDto cartItem : cartItemDtoList) {
            sum += cartItem.getQuantity() * cartItem.getPrice();
        }
        modelAndView.addObject("sum", sum);
        return modelAndView;
    }

    @GetMapping("/cart/")
    public ModelAndView getCartPage(ModelAndView modelAndView) {
        List<CartItemDto> cartItemDtoList = cartItemService.getListOfCartItemDTOs();
        modelAndView.addObject("cart", cartItemDtoList);
        modelAndView.setViewName("cart");
        Long sum = 0L;
        for (CartItemDto cartItem : cartItemDtoList) {
                sum += cartItem.getQuantity() * cartItem.getPrice();
        }
        modelAndView.addObject("sum", sum);
        return modelAndView;
    }

    @PatchMapping("/cart/editQuantity")
    public ModelAndView editQuantityInTheCart(@ModelAttribute("item") CartItemDto item, @RequestParam Long id, ModelAndView modelAndView) {
        cartItemService.updateQuantity(item);
        modelAndView.setViewName("redirect:/cart/");
        return modelAndView;
    }


    @DeleteMapping("/cart/deleteItem")
    public ModelAndView deleteCartItem(@RequestParam Long cartItemId, ModelAndView modelAndView) {
        cartItemService.deleteCartItem(cartItemId);
        modelAndView.setViewName("redirect:/cart/");
        return modelAndView;
    }

}
