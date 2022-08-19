package com.amr.project.webapp.controller;

import com.amr.project.converter.ItemToItemForShowcaseDtoConverter;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ReviewService;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;
    private ReviewService reviewService;
    private UserService userService;

    @Autowired
    public ItemController(ItemService itemService, ReviewService reviewService, UserService userService) {
        this.itemService = itemService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model, @ModelAttribute("review") ReviewDto review) {
        Item item = itemService.getItemById(id);
        model.addAttribute("item", ItemToItemForShowcaseDtoConverter.convertItemToItemForShowcaseDto(item));
        List<Review> list = reviewService.findReviewsByItem(item);
//                .stream()
//                .filter(Review::isModerated).collect(Collectors.toList());

        model.addAttribute("reviews", list);
        return "itemInfo";
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        try {
            if (user.getRole() == Roles.ADMIN || user.getRole() == Roles.MODERATOR) {
                itemService.deleteItem(id);
            }
        } catch (NullPointerException e) {
            return "Ошибка доступа";
        } finally {
            return "redirect:/";
        }
    }
    @GetMapping("/{id}/edit")
    public String editItem(Model model, @PathVariable("id") long id) {
        Item editItem = itemService.getItemById(id);
        model.addAttribute("item", editItem);
        return "editItem";
    }
    @PatchMapping("{id}")
    public String updateItem(ItemDtoRequest itemDtoRequest,
                             @PathVariable("id") Long id) {
        itemService.updateItem(id, itemDtoRequest);
        return "redirect:/";
    }
    @GetMapping("/newItem")
    public String newItem(Model model) {
        model.addAttribute("item", new Item());
        return "newItem";
    }
    @PostMapping
    public String createItem(ItemDtoRequest itemDtoRequest) {
        itemService.addItem(itemDtoRequest);
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String createNewReview(@PathVariable("id") Long id,
                                  Principal principal,
                                  Model model,
                                  @ModelAttribute("review") ReviewDto review) {
            //Через ДТО не работает из-за маппера юзера
          User user = userService.findByUserName(principal.getName());
        review.setId(null);
        review.setItemId(id);
        review.setShopId(itemService.getItemById(id).getShop().getId());
        review.setUserName(user.getUsername());

        reviewService.addReviewDto(review);

//            review.setId(null);
//            review.setUser(user);
//            review.setItem(itemService.getItemById(id));
//            review.setDate(new Date());
//            review.setShop(itemService.getItemById(id).getShop());
//            reviewService.persist(review);
        return "redirect:/items/" + id;
    }

}
