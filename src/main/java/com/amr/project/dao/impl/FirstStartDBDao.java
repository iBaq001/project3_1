package com.amr.project.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FirstStartDBDao<T> {

    @PersistenceContext
    EntityManager emStart;

    @Transactional
    public void persist(T entity) {
        emStart.persist(entity);
    }

}

