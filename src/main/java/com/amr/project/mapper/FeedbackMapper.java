package com.amr.project.mapper;

import com.amr.project.model.dto.FeedbackDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Feedback;
import com.amr.project.model.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = UserMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FeedbackMapper {

    @Mapping(target = "userId", source = "user.id")
    FeedbackDto feedbackToFeedbackDto(Feedback feedback);

    @Named("user")
    @Mapping(source = "userId", target = "id")
    UserDto userToUSerDto(FeedbackDto dto);
    @Named("user")
    @Mapping(source = "id", target = "id")
    User userDtoToUser(UserDto dto);

//    @Override
    @Mapping(source = "dto", target = "user", qualifiedByName = "user")
    Feedback feedbackDtoToFeedback(FeedbackDto dto);









//    Feedback feedbackDtoToFeedback(FeedbackDto feedbackDto);

//    @Autowired
//    UserService userService;
//    @Mapping(source = "feedback.user", target = "userId", qualifiedByName = "getIdFromUser")
//     FeedbackDto feedbackToFeedbackDto(Feedback feedback);
//    @Named("getIdFromUser")
//     default Long getIdFromUser(User user) {
//        return user.getId();
//    }


//    @Mapping(source = "feedbackDto.userId", target = "user", qualifiedByName = "getUserById")
//    default Feedback feedbackDtoToFeedback(FeedbackDto feedbackDto, User user){
//        Feedback feedback = new Feedback();
//        feedback.setUser(user);
//        return feedback;
//    }



//    @Named("getUserById")
//    public default User getUserById(Long id) {
//        return .findById(id);
//    }
}
