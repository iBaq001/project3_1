package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Order;

import java.util.List;

public interface OrderDao extends ReadWriteDao<Order, Long> {
    List<Item> itemsListByShopId(Long shopId);
}
