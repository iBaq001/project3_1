package com.amr.project.service.abstracts;

import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;

import java.util.List;

public interface FeedbackService extends ReadWriteService<Feedback, Long> {
    List<FeedbackDto> findAllFeedbackDto();

    FeedbackDto getFeedbackDtoById(Long id);

    Feedback persist(FeedbackDto feedbackDto, User user);

    void updateFeedback(Long id, FeedbackDto feedbackDto);

    void deleteFeedback(Long id);
}
