package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.model.entity.Category;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class CategoryDaoImpl extends ReadWriteDaoImpl<Category, Long> implements CategoryDao {
    @Override
    public List<Category> findAllCategoriesByShopId(Long shopId) {
        TypedQuery<Category> query = em.createQuery("select category from Category category where category.shopId = :shopId", Category.class);
        return query.setParameter("shopId", shopId).getResultList();
    }
}
