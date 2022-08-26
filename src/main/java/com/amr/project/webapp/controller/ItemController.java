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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    @GetMapping
    public String getItems(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "1") int size,
            Model model) {
        Page<ItemForShowcaseDto> pages = itemService.findAll(PageRequest.of(page, size));
        model.addAttribute("page", pages);
        model.addAttribute("numbers", IntStream.range(0,pages.getTotalPages()).toArray());
        model.addAttribute("items", pages.getContent());
        return "items";
    }

    @GetMapping("admin/items")
    public String itemList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("items", itemService.getAll());
        model.addAttribute("user", userService.findByUserName(user.getUsername()));
        return "admin_items";
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
    public ModelAndView deleteItem(@PathVariable("id") Long id, ModelAndView modelAndView) {
        itemService.deleteItem(id);
        modelAndView.setViewName("redirect:/items");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public String editItem(Model model, @PathVariable("id") long id) {
        Item editItem = itemService.getItemById(id);
        model.addAttribute("item", editItem);
        return "editItem";
    }

    @PatchMapping("/{id}")
    public String updateItem(ItemDtoRequest itemDtoRequest,
                             @PathVariable("id") Long id) {
        itemService.updateItem(id, itemDtoRequest);
        return "redirect:/items";
    }

    @GetMapping("/newItem")
    public String newItem(Model model) {
        model.addAttribute("item", new Item());
        return "newItem";
    }

    @PostMapping
    public String createItem(ItemDtoRequest itemDtoRequest, @ModelAttribute("item") Item item) {
        itemService.addItem(itemDtoRequest);
        return "redirect:/items";
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
        review.setDate(LocalDate.now());
        review.setShopId(itemService.getItemById(id).getShop().getId());
        review.setUserName(user.getUsername());
        reviewService.addReviewDto(review);
        return "redirect:/items/" + id;
    }

}
