package com.ousl.examinations.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ousl.examinations.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDetailsImplementation implements UserDetails {

    private User user;;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImplementation(User user) {
        this.user = user;
        this.authorities = this.user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .toList();
    }

    public static UserDetailsImplementation build(User user){
        return new UserDetailsImplementation(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.isStatus();
    }

    public Long getId(){
        return user.getId();
    }

    public String getEmail(){
        return user.getEmail();
    }

}
