package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReviewDao;
import com.amr.project.mapper.ReviewMapper;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewService {
    private final ReviewDao reviewDao;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewDao reviewDao, ReviewMapper reviewMapper) {
        super(reviewDao);
        this.reviewDao = reviewDao;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> findAllReviewsDto() {
        return reviewDao.findAll().stream()
                .map(reviewMapper::reviewToReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto findReviewDtoById(Long id) {
        return reviewMapper.reviewToReviewDto(findById(id));
    }

    @Override
    public void updateReviewDto(Long id, ReviewDto reviewDto) {
        reviewDao.update(reviewMapper.reviewDtoToReview(reviewDto));
    }

    @Override
    public void addReviewDto(ReviewDto reviewDto) {
        reviewDao.persist(reviewMapper.reviewDtoToReview(reviewDto));
    }
}
