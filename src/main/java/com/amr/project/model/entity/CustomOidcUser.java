package com.amr.project.model.entity;

import com.amr.project.model.enums.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class CustomOidcUser implements OidcUser, UserDetails {

    private OidcUser oidcUser;
    private String username;
    private boolean activate;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public CustomOidcUser(OidcUser oidcUser) {
        this.oidcUser = oidcUser;
    }

    @Override
    public Map<String, Object> getClaims() {
        return oidcUser.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return oidcUser.getIdToken();
    }

    @Override
    public <A> A getAttribute(String name) {
        return OidcUser.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(Arrays.asList(role));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activate;
    }

    @Override
    public String getName() {
        return oidcUser.getAttribute("name");
    }

    public String getEmail() {
        return oidcUser.getAttribute("email");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
