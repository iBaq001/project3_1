package com.amr.project.converter;

import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;

public class DiscountToDiscountDtoConverter {

    public static DiscountDto convertDiscountToDiscountDto(Discount discount) {
        return new DiscountDto(discount.getId(),
                discount.getMinOrder(),
                discount.getPercentage(),
                discount.getFixedDiscount(),
                null,
                (discount.getShop() == null) ? null : discount.getShop().getId());
    }
}
