package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReviewDao;
import com.amr.project.mapper.ReviewMapper;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ReviewService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewService {
    private final ReviewDao reviewDao;
    private final ReviewMapper reviewMapper;
    private  final UserService userService;

    public ReviewServiceImpl(ReviewDao reviewDao, ReviewMapper reviewMapper, UserService userService) {
        super(reviewDao);
        this.reviewDao = reviewDao;
        this.reviewMapper = reviewMapper;
        this.userService = userService;
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
        Review review =  reviewMapper.reviewDtoToReview(reviewDto);
        User user = userService.findByUserName(reviewDto.getUserName());
        review.setUser(user);
        reviewDao.persist(review);


    }

    @Override
    public List<Review> findReviewsByItem(Item item) {
        return reviewDao.findReviewsByItem(item);
    }

    @Override
    public Review findReviewByUser(User user){
        return  reviewDao.findReviewByUser(user);
    }

}
