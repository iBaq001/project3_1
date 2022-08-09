package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Chat;
import com.amr.project.model.entity.User;

import java.util.Optional;

public interface ChatService {
    Optional<Chat> findBySenderAndRecipient(User sender, User recipient, boolean createIfNotExist);

    Long getChatId(Chat chat);

    Chat save(Chat chat);
}
