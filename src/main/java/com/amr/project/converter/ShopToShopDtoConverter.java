package com.amr.project.converter;

import com.amr.project.model.dto.*;
import com.amr.project.model.entity.Shop;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class ShopToShopDtoConverter {

    public static ShopDto convertShopToShopDto(Shop shop) {
        if(shop == null) {
            return null;
        }
        return new ShopDto(shop.getId(),
                shop.getName(),
                shop.getDescription(),
                shop.getEmail(),
                shop.getPhone(),
                shop.getRating(),
                (shop.getReviews() == null) ? null : shop.getReviews().stream()
                        .map(review -> ReviewToReviewDtoConverter.convertReviewToReviewDto(review))
                        .collect(Collectors.toList()),
                ImageToImageDtoConverter.convertImageToImageDto(shop.getLogo()),
                (shop.getDiscounts() == null) ? null : shop.getDiscounts().stream()
                        .map(discount -> DiscountToDiscountDtoConverter.convertDiscountToDiscountDto(discount))
                        .collect(Collectors.toList()),
                ((shop.getAddress() == null) || (shop.getAddress().getCity() == null)) ? null : CityToCityDtoConverter
                        .convertCityToCityDto(shop.getAddress().getCity()),
                (shop.getUser() == null) ? null : shop.getUser().getId(),
                (shop.getCoupons() == null) ? null : shop.getCoupons().stream()
                        .map(coupon -> coupon.getId())
                        .collect(Collectors.toList()),
                shop.isModerated());
    }
}
