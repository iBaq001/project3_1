package com.amr.project.webapp.controller;

import com.amr.project.mapper.ReviewMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ItemDtoRequest;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.*;
import com.amr.project.service.impl.MailSenderRealizationImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/moderator/")
@RequiredArgsConstructor
public class ModeratorController {
    private final RestItemController itemController;
    private final ShowcaseRestController showcaseRestController;
    private final ReviewController reviewController;

    private final MailSenderRealizationImpl emailSenderService;

    private final ReviewService reviewService;


    private final UserService userService;



    private final ReviewMapper reviewMapper;
    private final ShowcaseService showcaseService;
    private ShopService shopService;
    private ItemService itemService;


    @GetMapping("/items")
    public String getAllItems(Model model){
        List<ItemDto> unmoderatedItemList = itemController.getAllItems()
                .stream()
                .filter(itemDto -> !itemDto.isModerated())
                .collect(Collectors.toList());

        model.addAttribute("items", unmoderatedItemList);
        return "unchecked_items";
    }

    @PutMapping("/items/accept/{id}")
    public String updateItems(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        System.out.println(item);
        item.setModerated(true);
        item.setModerateAccept(true);
        itemService.update(item);
        return "redirect:/api/moderator/items";
    }
    @PatchMapping("/items/reject/{id}")
    public String updateItems2 (@PathVariable Long id,
                                @RequestParam(value = "reason") String reason) {
        Item item = itemService.getItemById(id);
        item.setModerated(true);
        item.setModerateAccept(false);
        item.setModeratedRejectReason(reason);
        itemService.update(item);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText("Ваш товар отклонен по причине: "
                + reason);
        emailSenderService.send(item.getShop().getEmail(), "E-mail verification", String.valueOf(mailMessage));

        return "redirect:/api/moderator/items";

    }




//    @GetMapping("/items")
//    public ResponseEntity<List<ItemDto>> getAllItems() {
//
//        List<ItemDto> unmoderatedItemList = itemController.getAllItems()
//                .stream()
//                .filter(itemDto -> !itemDto.isModerated())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(unmoderatedItemList);
//    }

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

//    @GetMapping("shops")
//    public ResponseEntity<List<ShopDto>> getAllShops() {
//        List<ShopDto> unmoderatedShopList = showcaseRestController.getAllShopDto()
//                .stream()
//                .filter(shopDto -> !shopDto.isModerated())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(unmoderatedShopList);
//    }

    @GetMapping("/shops")
    public String getAllShops(Model model){
        List<ShopDto> unmoderatedShopList = showcaseService.getAllShopDto()
                .stream()
                .filter(shopDto -> !shopDto.isModerated())
                .collect(Collectors.toList());

        model.addAttribute("shops", unmoderatedShopList);
        return "unchecked_shops";
    }


    @PutMapping("/shops/accept/{id}")
    public String updateShops(@PathVariable Long id) {

        ShopDto shopDto = showcaseService.getShopDtoById(id);
        System.out.println(shopDto);
        shopDto.setModerated(true);
        shopDto.setModeratedAccept(true);
        showcaseService.updateShopDto(shopDto);
        return "redirect:/api/moderator/shops";
    }
    @PatchMapping("/shops/reject/{id}")
    public String updateShops2 (@PathVariable Long id,
                                @RequestParam(value = "reason") String reason) {
        ShopDto shopDto = showcaseService.getShopDtoById(id);
        shopDto.setModerated(true);
        shopDto.setModeratedAccept(false);
        shopDto.setModeratedRejectReason(reason);
        showcaseService.updateShopDto(shopDto);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText("Ваш магазин отклонен по причине: "
                + reason);
        emailSenderService.send(shopDto.getEmail(), "E-mail verification", String.valueOf(mailMessage));

        return "redirect:/api/moderator/shops";

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
    public String getAllReviews(Model model) {
        List<ReviewDto> unmoderatedReviewList = reviewController.getAllReviews()
                .stream()
                .filter(reviewDto -> !reviewDto.isModerated())
                .collect(Collectors.toList());
        model.addAttribute("reviews", unmoderatedReviewList);
        return "unchecked_reviews";
    }

//    @GetMapping("/reviews")
//    public ResponseEntity<List<ReviewDto>> getAllReviews() {
//        List<ReviewDto> unmoderatedReviewList = reviewController.getAllReviews()
//                .stream()
//                .filter(reviewDto -> !reviewDto.isModerated())
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(unmoderatedReviewList);
//    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDto> getOneReview(@PathVariable Long id) {
        ReviewDto reviewDto = reviewController.getReviewById(id);
        if (reviewDto == null || reviewDto.isModerated()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewDto);
    }

    @PutMapping("/reviews/accept/{id}")
    public String updateReview(@PathVariable Long id) {

        ReviewDto reviewDto = reviewController.getReviewById(id);

        reviewDto.setModerated(true);
        reviewDto.setModeratedAccept(true);
        reviewController.updateReview(id, reviewDto);
        return "redirect:/api/moderator/reviews";
    }
    @PatchMapping("/reviews/reject/{id}")
    public String updateReview2 (@PathVariable Long id,
                                 @ModelAttribute("review") ReviewDto reviewDto,
                                 @RequestParam(value = "reason") String reason) {
        Review review = reviewService.findById(id);

        review.setModerated(true);
        review.setModerateAccept(false);
        review.setModeratedRejectReason(reason);
        reviewService.update(review);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText("Ваш отзыв отклонен по причине: "
                + reason);
        emailSenderService.send(review.getUser().getEmail(), "E-mail verification", String.valueOf(mailMessage));

        return "redirect:/api/moderator/reviews";

    }

//    @PutMapping("/reviews/{id}")
//    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id,
//                                                  @RequestBody ReviewDto reviewDto1) {
//        ReviewDto reviewDto = reviewController.getReviewById(id);
//        if (reviewDto.isModerated()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(reviewDto1);
//    }
}
