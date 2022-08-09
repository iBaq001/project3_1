package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.MessageDao;
import com.amr.project.model.entity.Message;
import com.amr.project.service.abstracts.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;

    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Message save(Message message) {
        messageDao.persist(message);
        return message;
    }
}
