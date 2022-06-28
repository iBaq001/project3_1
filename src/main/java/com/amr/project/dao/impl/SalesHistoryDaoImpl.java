package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.SalesHistoryDao;
import com.amr.project.model.entity.report.SalesHistory;
import org.springframework.stereotype.Repository;

@Repository
public class SalesHistoryDaoImpl extends ReadWriteDaoImpl<SalesHistory, Long> implements SalesHistoryDao {
}
