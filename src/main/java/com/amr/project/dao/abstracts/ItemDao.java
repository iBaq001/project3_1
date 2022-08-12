package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemDao extends ReadWriteDao<Item, Long> {

    List<Item> getItemsByShopId(Long shopId);

    List<Item> getItemsByName(String name);

     List<Item> getBestRatingItems (int limit);

}
