package com.amr.project.converter;

import com.amr.project.model.dto.ItemForShowcaseDto;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Item;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemToItemForShowcaseDtoConverter {


    public static ItemForShowcaseDto convertItemToItemForShowcaseDto(Item item) {
        if (item == null) {
            return null;
        }
        ItemForShowcaseDto result = new ItemForShowcaseDto();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setDescription(item.getDescription());
        result.setBasePrice(item.getBasePrice());
        result.setPrice(item.getPrice());
        result.setCount(item.getCount());
        result.setImageDto(ImageToImageDtoConverter.convertImageToImageDto(
                item.getImages().stream().filter(Image::getIsMain).findFirst().orElse(null)));
        result.setShopDto(ShopToShopDtoConverter.convertShopToShopDto(item.getShop()));
        result.setRating(item.getRating());

        // ниже махинации для построения нужного количества звезд рейтинга в карточке товара на витрине средствами thymeleaf
        int ratingInt = (int) Math.round(item.getRating());
        List filledStars = new ArrayList();
        for (int i = 0; i < ratingInt; i++) {
            filledStars.add(new Object());
        }
        result.setRatingFilledStars(filledStars);

        List emptyStars = new ArrayList();
        for (int i = 0; i < (5 - ratingInt); i++) {
            emptyStars.add(new Object());
        }
        result.setRatingEmptyStars(emptyStars);
        return result;
    }
}
