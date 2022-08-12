package com.amr.project.mapper;

import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemMapper.class, UserMapper.class, ShopMapper.class})
public interface ReviewMapper {
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    ReviewDto reviewToReviewDto(Review review);

    @Mapping(target = "user.username", source = "userName")
    @Mapping(target = "item.id", source = "itemId")
    @Mapping(target = "shop.id", source = "shopId")
    @Mapping(target = "user.id", source = "userId")
    Review reviewDtoToReview(ReviewDto reviewDto);
}
