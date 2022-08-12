package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReviewDao;

import com.amr.project.model.entity.Review;

import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

import com.amr.project.model.entity.Item;

@Repository
public class ReviewDaoImpl extends ReadWriteDaoImpl<Review, Long> implements ReviewDao {


    @Override
    public List<Review> findReviewsByItem(Item item) {
        TypedQuery<Review> query = em.createQuery("select r from Review r where  r.item = :item", Review.class);
        return query.setParameter("item", item).getResultList();

    }

    @Override
    public Review findReviewByUser(User user) {
        try {
            TypedQuery<Review> query = em.createQuery("select r from Review r where  r.user = :user", Review.class);
            return query.setParameter("user", user).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
