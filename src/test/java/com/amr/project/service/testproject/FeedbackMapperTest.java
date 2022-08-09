package com.amr.project.service.testproject;

import com.amr.project.mapper.FeedbackMapper;
import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
import com.amr.project.model.enums.Roles;
import com.amr.project.service.abstracts.UserService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackMapperTest {
    private final FeedbackMapper feedbackMapper = Mappers.getMapper(FeedbackMapper.class);

    User user = new User(1l, "@mail.ru", "username", "pass", "phone", "firstname", "lastname", 22,
            Gender.MALE, Calendar.getInstance(),true, "code", false, "secret", false, Roles.USER,
            PersonalData.builder().build(), Favorite.builder().build(),
            Address.builder().build(), null, null, null, null, null, null, null,
            null, null, null,null);

    @Test
    void feedbackToFeedbackDto() {

        Feedback feedback = new Feedback();

        feedback.setUser(user);
        feedback.setDateTime(LocalDateTime.now());
        feedback.setFullText("OK");
        feedback.setId(1L);
        feedback.setReason("good");
        feedback.setUsername("username");

        FeedbackDto feedbackDto = feedbackMapper.feedbackToFeedbackDto(feedback);

        assertEquals(feedback.getId(), feedbackDto.getId());
        assertEquals(feedback.getDateTime(), feedbackDto.getDateTime());
        assertEquals(feedback.getFullText(), feedbackDto.getFullText());
        assertEquals(feedback.getReason(), feedbackDto.getReason());
        assertEquals(feedback.getUser().getId(), feedbackDto.getUserId());
    }

    @Test
    void feedbackDtoToFeedback() {

        FeedbackDto feedbackDto = new FeedbackDto();

        feedbackDto.setReason("reason");
        feedbackDto.setDateTime(LocalDateTime.now());
        feedbackDto.setUserId(1L);
        feedbackDto.setFullText("fullText");
        feedbackDto.setId(1L);

        Feedback feedback = feedbackMapper.feedbackDtoToFeedback(feedbackDto);

        assertEquals(feedbackDto.getUserId(), feedback.getUser().getId());
        assertEquals(feedbackDto.getReason(), feedback.getReason());
        assertEquals(feedbackDto.getId(), feedback.getId());
        assertEquals(feedbackDto.getFullText(), feedback.getFullText());
        assertEquals(feedbackDto.getDateTime(), feedback.getDateTime());

    }
}