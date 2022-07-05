package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class UserDaoImpl extends ReadWriteDaoImpl<User, Long> implements UserDao {


    @Override
    public User findUserByEmail(String email) {
        TypedQuery<User> query = em.createQuery(
                "select user from User user where user.email = :email", User.class);
        return query
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User findUserByUsername(String username) {
        TypedQuery<User> query = em.createQuery(
                "select user from User user where user.username = :username", User.class);
        return query
                .setParameter("username", username)
                .getSingleResult();
    }
}
