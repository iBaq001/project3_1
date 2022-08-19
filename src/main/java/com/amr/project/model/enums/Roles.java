package com.amr.project.model.enums;

import org.springframework.security.core.GrantedAuthority;


public enum Roles implements GrantedAuthority{
    USER("USER"),
    MODERATOR("MODERATOR"),
    ADMIN("ADMIN"),
    ANONYMOUS("ANONYMOUS"),;

    private String name;


    Roles(String name) {
        this.name = name;
    }

    Roles() {
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
