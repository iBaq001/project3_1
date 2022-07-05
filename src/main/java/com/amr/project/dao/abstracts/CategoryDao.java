package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Category;

import java.util.List;

public interface CategoryDao extends ReadWriteDao<Category,Long> {
    public List<Category> findAllCategoriesByShopId(Long shopId);
}
