package com.pizza.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Collectors;


public class PizzaUserDetails implements UserDetails {
    private String username;
    private String password;
    private String phoneNumber;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private List<GrantedAuthority> authorities;

    public PizzaUserDetails(PizzaUser pizzaUser) {
        this.username = pizzaUser.getUsername();
        this.password = pizzaUser.getPassword();
        this.phoneNumber = pizzaUser.getPhoneNumber();
        this.accountNonExpired = pizzaUser.isActive();
        this.authorities = Arrays.stream(pizzaUser.getRoles().split(","))
                .map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
        return true;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
