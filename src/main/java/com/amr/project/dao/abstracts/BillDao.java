package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Bill;

import java.util.List;


public interface BillDao extends ReadWriteDao<Bill, Long> {
    public Bill findById(Long id);
    List<Bill> findAll();
    void delete(Bill bill);
    void update(Bill bill);
    void persist (Bill bill);



}
