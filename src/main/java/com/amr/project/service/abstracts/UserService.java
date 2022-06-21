package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public List<User> findAll();

    public User findById(Long id);

    public void deleteById(Long id);

    public void persist(User user);

    public void update(User user);

}
