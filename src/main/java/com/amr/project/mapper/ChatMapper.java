package com.amr.project.mapper;

import com.amr.project.model.dto.ChatDto;
import com.amr.project.model.dto.MessageDto;
import com.amr.project.model.entity.Chat;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, MessageMapper.class})
public interface ChatMapper {

    Chat toEntity(ChatDto chatDto);

    default ChatDto toDto(Chat chat) {

        MessageMapper messageMapper = new MessageMapperImpl();
        if (chat == null) {
            return null;
        }

        Long senderId = chat.getSender().getId();
        Long recipientId = chat.getRecipient().getId();

        Set<Long> membersId = new HashSet<>(List.of(senderId, recipientId));


        List<MessageDto> messagesDto =
                chat.getMessages().stream().map(messageMapper::toDto).collect(Collectors.toList());

        return new ChatDto(chat.getId(),
                chat.getHash(),
                membersId,
                messagesDto);
    }
}
