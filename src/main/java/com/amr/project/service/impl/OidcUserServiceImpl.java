package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.CustomOidcUser;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class OidcUserServiceImpl extends OidcUserService {

    @Autowired
    UserDao userDao;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        CustomOidcUser customOidcUser = new CustomOidcUser(oidcUser);

        String username = oidcUser.getAttributes().get("username").toString();
        User user = createUserIfNoExist(username, oidcUser);
        customOidcUser.setUsername(user.getUsername());
        customOidcUser.setRole(user.getRole());
        return customOidcUser;
    }

    public User createUserIfNoExist(String username, OidcUser oidcUser) {
        User user = userDao.findUserByUsername(username);

        if (user == null) {
            user = User.builder()
                    .isUsing2FA(false)
                    .isIdentification(true)
                    .activate(true)
                    .email(oidcUser.getEmail())
                    .username(username)
                    .role(Roles.USER)
                    .build();
            userDao.persist(user);
        }
        return user;
    }
}
