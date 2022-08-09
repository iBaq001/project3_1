package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ChatDao;
import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ChatService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatDao chatDao;

    public ChatServiceImpl(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    // TODO return DTO obj
    public Optional<Chat> findBySenderAndRecipient(User sender, User recipient, boolean createIfNotExist) {
        return Optional.of(chatDao.findBySenderAndRecipientId(sender.getId(), recipient.getId()))
                .or(() -> {
                    if (!createIfNotExist) {
                        return Optional.empty();
                    }
                    Chat newChat = Chat.builder()
                            .sender(sender)
                            .recipient(recipient)
                            .build();

                    chatDao.persist(newChat);
                    return Optional.of(newChat);
                });
    }

    @Override
    public Long getChatId(Chat chat) {
        return chat.getId();
    }

    @Override
    public Chat save(Chat chat) {
        chatDao.persist(chat);
        return chat;
    }
}
