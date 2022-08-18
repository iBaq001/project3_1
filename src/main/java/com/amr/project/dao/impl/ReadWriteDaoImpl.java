package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;


public class ReadWriteDaoImpl<T, K> implements ReadWriteDao<T, K> {
    private final Class<T> clazz;

    @PersistenceContext
    protected EntityManager em;

    @SuppressWarnings("unchecked")
    public ReadWriteDaoImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Transactional
    @Override
    public void persist(T entity) {
        em.persist(entity);
    }

    @Transactional
    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
    @Transactional
    @Override
    public void deleteByIdCascadeEnable(K id) {
        em.remove(em.find(clazz, id));
    }
    @Transactional
    @Override
    public void deleteByIdCascadeIgnore(K id) {
        em.createQuery("DELETE FROM " + clazz.getName() + " u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existsById(K id) {
        return em.find(clazz, id) != null;
    }


    @Override
    public T findById(K id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("select u from " + clazz.getName() + " u", clazz)
                .getResultList();
    }
}
