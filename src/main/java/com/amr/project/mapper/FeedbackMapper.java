package com.amr.project.mapper;

import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class FeedbackMapper {
    @Autowired
    UserService userService;
    @Mapping(source = "feedback.user", target = "userId", qualifiedByName = "getIdFromUser")
    public abstract FeedbackDto feedbackToFeedbackDto(Feedback feedback);
    @Named("getIdFromUser")
    public Long getIdFromUser(User user) {
        return user.getId();
    }
    @Mapping(source = "userId", target = "user", qualifiedByName = "getUserById")
    public abstract Feedback feedbackDtoToFeedback(FeedbackDto feedbackDto);

    @Named("getUserById")
    public User getUserById(Long id) {
        return userService.findById(id);
    }
}
