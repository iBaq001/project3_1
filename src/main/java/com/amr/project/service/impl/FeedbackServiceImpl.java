package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.FeedbackDao;
import com.amr.project.mapper.FeedbackMapper;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.FeedbackService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class FeedbackServiceImpl extends ReadWriteServiceImpl<Feedback, Long> implements FeedbackService {

    final FeedbackDao feedbackDao;
    final FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackMapper feedbackMapper) {
        super(feedbackDao);
        this.feedbackDao = feedbackDao;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public List<FeedbackDto> findAllFeedbackDto() {
        return feedbackDao.findAll().stream()
                .map(feedbackMapper::feedbackToFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto getFeedbackDtoById(Long id) {
        return feedbackMapper.feedbackToFeedbackDto(findById(id));
    }

    @Override
    public Feedback persist(FeedbackDto feedbackDto, User user) {
        Feedback feedback = feedbackMapper.feedbackDtoToFeedback(feedbackDto);
        if (user != null) {
            feedback.setUser(user);
            feedback.setUsername(user.getUsername());
        }
        feedback.setDateTime(LocalDateTime.now());
        return super.persist(feedback);
    }


    @Override
    public void updateFeedback(Long id, FeedbackDto feedbackDto) {
        feedbackDao.update(feedbackMapper.feedbackDtoToFeedback(feedbackDto));
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackDao.delete(findById(id));
    }
}
