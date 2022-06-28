package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CategoryDaoImpl extends ReadWriteDaoImpl<Category, Long> implements CategoryDao {
    @Override
    public Set<Category> findAllCategoriesByShopId(Long shopId) {
        TypedQuery<Category> query = em.createQuery("select category from Category category", Category.class);
        Set<Item> itemList = query.getResultStream().flatMap(category -> category.getItems()
                .stream().filter(item -> item.getShop().getId().equals(shopId))).collect(Collectors.toSet());
        return itemList.stream().map(item -> item.getCategory()).collect(Collectors.toSet());
    }
}
