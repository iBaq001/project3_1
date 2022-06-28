package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Category;

import java.util.Set;

public interface CategoryDao extends ReadWriteDao<Category,Long> {
    public Set<Category> findAllCategoriesByShopId(Long shopId);
}
