package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.SalesHistoryDao;
import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.report.SalesHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SalesHistoryDaoImpl extends ReadWriteDaoImpl<SalesHistory, Long> implements SalesHistoryDao {
    @Override
    public List<Category> findAllCategoriesByShopId(Long shopId) {
        TypedQuery<Category> query = em.createQuery("select category from Category category where category.shopId = :shopId", Category.class);
        return query.setParameter("shopId", shopId).getResultList();
    }
}
