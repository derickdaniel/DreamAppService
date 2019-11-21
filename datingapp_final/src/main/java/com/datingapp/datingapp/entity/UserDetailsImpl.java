package com.datingapp.datingapp.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private String phoneNumber;
    private String apiKey;

    public UserDetailsImpl(final User user) {
        this.phoneNumber = user.getPhoneNumber();
        this.apiKey = user.getApiKey();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return apiKey;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
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
