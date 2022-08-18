package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Order;
import com.amr.project.service.abstracts.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ReadWriteServiceImpl<Order, Long> implements OrderService {

    public OrderServiceImpl(ReadWriteDao<Order, Long> dao) {
        super(dao);
    }


}
