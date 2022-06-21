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

        String email = oidcUser.getAttributes().get("email").toString();
        User user = createUserIfNoExist(email, oidcUser);
        customOidcUser.setUsername(user.getUsername());
        customOidcUser.setRole(user.getRole());
        return customOidcUser;
    }

    public User createUserIfNoExist(String email, OidcUser oidcUser) {
        User user = userDao.findUserByEmail(email);

        if (user == null) {
            user = User.builder()
                    .isUsing2FA(false)
                    .isIdentification(true)
                    .activate(true)
                    .email(email)
                    .username(email)
                    .role(Roles.USER)
                    .build();
            userDao.save(user);
        }
        return user;
    }
}
