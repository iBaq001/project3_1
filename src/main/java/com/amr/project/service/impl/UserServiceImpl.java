package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void persist(User user) {
        userDao.persist(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDao.deleteByIdCascadeIgnore(id);
    }
    @Override
    public User findByUsername (String username){
       return userDao.findUserByUsername(username);
    }
    @Override
    public User findByEmail (String email){
        return userDao.findUserByEmail(email);
    }
}
