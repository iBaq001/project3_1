package com.amr.project.converter;

import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;

import java.time.ZoneId;

public class ReviewToReviewDtoConverter {

    public static ReviewDto convertReviewToReviewDto(Review review) {
        return new ReviewDto(review.getId(),
                review.getDignity(),
                review.getFlaw(),
                review.getText(),
                (review.getDate() == null) ? null : review.getDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(),
                review.getRating(),
                (review.getItem() == null) ? null : review.getItem().getId(),
                (review.getUser() == null) ? null : review.getUser().getId(),
                (review.getUser() == null) ? null : review.getUser().getUsername(),
                (review.getShop() == null) ? null : review.getShop().getId()
        );
    }
}
