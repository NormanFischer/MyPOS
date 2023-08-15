package com.norman.MyPosServer.Security;

import com.norman.MyPosServer.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserSecurity extends User implements UserDetails {

    public UserSecurity() {}
    public UserSecurity(User user) {
        this.setAuthorities(user.getAuthorities());
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setPassword(user.getPassword());
    }

    @Override
    public Set<Authority> getAuthorities() {
        return this.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getUsername();
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
        return true;
    }
}
