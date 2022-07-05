package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.report.SalesHistory;

import java.util.List;

public interface SalesHistoryDao extends ReadWriteDao<SalesHistory, Long> {
    public List<Category> findAllCategoriesByShopId(Long shopId);
}
