package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;
    private User user;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        user.getAuthorities();
        return user;
    }

//    public User findUserByEmail(String email) {
//        return userDao.findUserByEmail(email);
//    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public boolean isEnabled() {
        return user.isActivate();
    }


}
