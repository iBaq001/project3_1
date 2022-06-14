package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends ReadWriteDao<Item, Long> {

    public List<Item> getItemsByShopId(Long shopId);

    List<Item> getItemsByName(String name);

}
