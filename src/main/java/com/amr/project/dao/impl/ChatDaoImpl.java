package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ChatDao;
import com.amr.project.model.entity.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ChatDaoImpl extends ReadWriteDaoImpl<Chat, Long> implements ChatDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Chat findBySenderAndRecipientId(Long senderId, Long recipientId) {
        return em.createQuery("SELECT chat FROM Chat chat WHERE chat.sender = :sender AND chat.recipient = :recipient",
                        Chat.class)
                .setParameter("sender", senderId)
                .setParameter("recipient", recipientId)
                .getSingleResult();
    }

}
