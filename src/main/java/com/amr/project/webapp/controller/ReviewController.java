package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ReviewDto;
import com.amr.project.service.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.findAllReviewsDto();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.findReviewDtoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id,
                                                  @RequestBody ReviewDto reviewDto) {
        reviewService.updateReviewDto(id, reviewDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody ReviewDto reviewDto) {
        reviewService.addReviewDto(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
