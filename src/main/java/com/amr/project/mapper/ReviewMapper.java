package com.amr.project.mapper;

import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto reviewToReviewDto(Review review);
    Review reviewDtoToReview(ReviewDto reviewDto);
}
