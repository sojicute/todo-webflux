package com.github.sojicute.todowebflux.security;

import com.github.sojicute.todowebflux.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


public class MyUserDetails implements UserDetails {
    private final User user;
    Collection<GrantedAuthority> authorities;


    public MyUserDetails(User user, String[] roles) {
        this.authorities = Arrays.asList(roles)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
