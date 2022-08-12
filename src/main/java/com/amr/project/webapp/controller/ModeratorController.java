package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moderator/")
@RequiredArgsConstructor
public class ModeratorController {
    private final RestItemController itemController;
    private final ShowcaseRestController showcaseRestController;
    private final ReviewController reviewController;

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> unmoderatedItemList = itemController.getAllItems()
                .stream()
                .filter(itemDto -> !itemDto.isModerated())
                .collect(Collectors.toList());
        return ResponseEntity.ok(unmoderatedItemList);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDto> getOneItem(@PathVariable Long id) {
        ItemDto itemDto = itemController.getItemById(id);
        if (itemDto == null || itemDto.isModerated()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDto);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable Long id,
                                           @RequestBody ItemDtoRequest itemDtoRequest) {
        ItemDto itemDto = itemController.getItemById(id);
        if (itemDto.isModerated()) {
            return ResponseEntity.notFound().build();
        }
        return itemController.updateItem(id, itemDtoRequest);
    }

    @GetMapping("shops")
    public ResponseEntity<List<ShopDto>> getAllShops() {
        List<ShopDto> unmoderatedShopList = showcaseRestController.getAllShopDto()
                .stream()
                .filter(shopDto -> !shopDto.isModerated())
                .collect(Collectors.toList());
        return ResponseEntity.ok(unmoderatedShopList);
    }

    @GetMapping("shops/{id}")
    public ResponseEntity<ShopDto> getOneShop(@PathVariable Long id) {
        ShopDto shopDto = showcaseRestController.getShopDtoById(id);
        if (shopDto == null || shopDto.isModerated()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopDto);
    }

    @PutMapping("shops/{id}")
    public ResponseEntity<Void> updateShop(@PathVariable Long id) {
        ShopDto shopDto = showcaseRestController.getShopDtoById(id);
        if (shopDto.isModerated()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> unmoderatedReviewList = reviewController.getAllReviews()
                .stream()
                .filter(reviewDto -> !reviewDto.isModerated())
                .collect(Collectors.toList());
        return ResponseEntity.ok(unmoderatedReviewList);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDto> getOneReview(@PathVariable Long id) {
        ReviewDto reviewDto = reviewController.getReviewById(id);
        if (reviewDto == null || reviewDto.isModerated()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewDto);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id,
                                                  @RequestBody ReviewDto reviewDto1) {
        ReviewDto reviewDto = reviewController.getReviewById(id);
        if (reviewDto.isModerated()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewDto1);
    }
}
