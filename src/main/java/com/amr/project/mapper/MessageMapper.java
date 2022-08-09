package com.amr.project.mapper;

import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Message;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, ChatMapper.class})
public interface MessageMapper {

    Message toEntity(MessageDto messageDto);

    @Mapping(target = "creationTime", source = "message.date")
    @Mapping(target = "chatId", source = "message.chat.id")
    @Mapping(target = "fromUserId", source = "message.sender.id")
    @Mapping(target = "toUserId", source = "message.recipient.id")
    MessageDto toDto(Message message);
}
