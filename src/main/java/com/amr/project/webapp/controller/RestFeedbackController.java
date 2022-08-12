package com.amr.project.webapp.controller;

import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback/")
@RequiredArgsConstructor
public class RestFeedbackController {

    final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedback() {
        List<FeedbackDto> feedbackDtos = feedbackService.findAllFeedbackDto();
        return feedbackDtos !=null ? new ResponseEntity<>(feedbackDtos, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping( "/{id}")
    public ResponseEntity<FeedbackDto> feedbackById(@PathVariable Long id) {
        FeedbackDto feedbackDtos = feedbackService.getFeedbackDtoById(id);
        return ResponseEntity.ok(feedbackDtos);
    }
    @PostMapping
    public ResponseEntity<String> saveNewFeedback(@RequestBody FeedbackDto feedbackDto,
                                                  @AuthenticationPrincipal User user) {
        feedbackService.persist(feedbackDto, user);
        return new ResponseEntity<>("Feedback saved", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedbackById(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return feedbackService.existsById(id)
                ? new ResponseEntity<>("unsucces delete", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>("deleted succes", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable Long id,
                                           @RequestBody FeedbackDto feedbackDto) {
        feedbackService.updateFeedback(id, feedbackDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
