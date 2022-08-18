package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ReadWriteDao<User, Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);

    User findByActivationCode(String activationCode);
}
