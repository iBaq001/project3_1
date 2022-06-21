package com.amr.project.service.abstracts;

import com.amr.project.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public List<User> findAll();

    public Optional<User> findById(Long id);

    public User save(User user);

    public void deleteById(Long id);

}
