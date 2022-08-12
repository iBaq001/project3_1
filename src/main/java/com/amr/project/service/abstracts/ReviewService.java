package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.User;

import java.util.List;

public interface ReviewService extends ReadWriteService<Review, Long> {
    List<ReviewDto> findAllReviewsDto();
    ReviewDto findReviewDtoById(Long id);
    void updateReviewDto(Long id, ReviewDto reviewDto);
    void addReviewDto(ReviewDto reviewDto);
    List<Review> findReviewsByItem (Item item);
    Review findReviewByUser(User user);
}
