package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.MessageDao;
import com.amr.project.model.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDaoImpl extends ReadWriteDaoImpl<Message, Long> implements MessageDao {

}
