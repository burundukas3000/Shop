package com.project.backend.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

// Class for customizing login fields. UserDetails returned from UserRepo when loadUserByUserName implemented
// and used in loadUserByUserName for DaoAuthenticationProvider
public class UserDao extends org.springframework.security.core.userdetails.User {

    private String email;

    public UserDao(String username, String password, Collection<? extends GrantedAuthority> authorities, String email) {
        super(username, password, authorities);
        this.email = email;
    }

    public UserDao(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
    }

    public String getEmail() {
        return this.email;
    }
}
