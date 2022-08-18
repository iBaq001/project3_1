package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.mapper.CartItemMapper;
import com.amr.project.mapper.ItemMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {
    private final CartItemDao cartItemDao;
    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final ShopService shopService;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private User currentUser;


    @Autowired
    public CartItemServiceImpl(CartItemDao cartItemDao, ItemService itemService, ItemMapper itemMapper, ShopService shopService, UserService userService, CartItemMapper cartItemMapper) {
        super(cartItemDao);
        this.cartItemDao = cartItemDao;
        this.itemService = itemService;
        this.itemMapper = itemMapper;
        this.shopService = shopService;
        this.userService = userService;
        this.cartItemMapper = cartItemMapper;

    }


    // метод возвращает список элементов корзины по текущему юзеру установленному в поле currentUser
    public List<CartItem> getListOfCartItems(User user) {
        if (user == null) {
            return new ArrayList<CartItem>();
        } else {
            return cartItemDao.findCartItemByUserId(user.getId());
        }
    }


    // метод возвращает список элементов корзины по !текущему! юзеру установленному в поле currentUser

    @Override
    public List<CartItemDto> getListOfCartItemDTOs() {
        return getListOfCartItems(currentUser).stream().map(cartItemMapper::cartItemToCartItemDto).collect(Collectors.toList());
    }

    public void updateQuantityInCart(List<CartItemDto> cartItemDtoList) {
        cartItemDtoList.forEach(cartItemDto -> {
            CartItem cartItem = cartItemDao.findById(cartItemDto.getId());
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItemDao.update(cartItem);
        });
    }


    /* метод добавляет товары в корзину. Если строка с этим же товаром уже существует, то увеличивает количество в
    существующей строке вместо создания новой строки */
    @Override
    @Transactional
    public void addItemToCart(Long itemId, Long shopId, int quantity) {

        if (currentUser == null) {
            setUser(null);
        }
        CartItem cartItem;
        Optional<CartItem> opt = cartItemDao.findCartItem(itemId, shopId, currentUser.getId());
        if (opt.isPresent()) {
            cartItem = opt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemDao.update(cartItem);
        } else {
            cartItem = CartItem.builder().itemInCart(itemService.findById(itemId)).shop(shopService.findById(shopId)).user(currentUser).quantity(quantity).build();
            cartItemDao.persist(cartItem);
        }
    }


    private void changeUserInCartItems(User oldUser, User newUser) {
        //получаем список товаров по старому юзеру и меняем в них юзера на нового
        List<CartItem> list = getListOfCartItems(oldUser);
        if (list.isEmpty()) {
            return;
        }
        list.forEach(cartItem -> {
            cartItem.setUser(newUser);
            cartItemDao.update(cartItem);
        });
        userService.deleteById(oldUser.getId());
    }

    //метод ищет строки с одним и тем же товаром и магазином, и объединяет их
    private void joinSameCartItems(User user) {
        List<CartItem> list = getListOfCartItems(user);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); ) {
                if (list.get(i).getItemInCart() == list.get(j).getItemInCart()
                        && list.get(i).getShop() == list.get(j).getShop()) {
                    CartItem cartItem = list.get(i);
                    cartItem.setQuantity(list.get(i).getQuantity() + list.get(j).getQuantity());
                    list.set(i, cartItem);
                    cartItemDao.update(list.get(i));
                    cartItemDao.delete(list.get(j));
                    list.remove(j);
                } else {
                    j++;
                }
            }
        }
    }

    @Override
    @Transactional
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemDao.findById(cartItemId);
        if (cartItem == null) {
            return;
        } else if (cartItem.getUser().getId() == currentUser.getId()) cartItemDao.delete(cartItem);
    }

    @Override
    public String getCartItemName(Long itemId) {

        return itemService.getItemById(itemId).getName();
    }


    // метод устанавливает временного пользователя для возможности добавления товаров в корзину неаутентифицированным пользователем
    @Override
    public void setUser(User loggedUser) {
        if ( loggedUser == null && currentUser == null) {
            currentUser = User.builder()
                    .role(Roles.ANONYMOUS)
                    .build();
            userService.persist(currentUser);
        } else if (loggedUser != null && currentUser != loggedUser) {
            changeUserInCartItems(currentUser, loggedUser); //меняем юзера на loggedUser в строках корзины временного currentUser
            joinSameCartItems(loggedUser);
            currentUser = loggedUser;
        } else {
            currentUser = loggedUser;
        }

    }

    @Override
    public void updateQuantity(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemDao.findById(cartItemDto.getId());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItemDao.update(cartItem);
    }


    //Дальше методы-прослойки для тестирования

    public List<CartItem> getListOfCartItemsTest(User user){
        return getListOfCartItems(user);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void joinSameCartItemsTest(User user) {
        joinSameCartItems(user);
    }

}