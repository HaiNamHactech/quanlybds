package com.bkap.utils;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyUser  extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private String fullName ;

    private com.bkap.entities.User user;

    public MyUser(String username, String password, boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked,
                  Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public com.bkap.entities.User getUser() {
        return user;
    }


    public void setUser(com.bkap.entities.User user) {
        this.user = user;
    }

}
