package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Chat;

public interface ChatDao extends ReadWriteDao<Chat, Long> {

    Chat findBySenderAndRecipientId(Long senderId, Long recipientId);
}
