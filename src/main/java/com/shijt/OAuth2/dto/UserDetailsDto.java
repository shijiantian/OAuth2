package com.shijt.OAuth2.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsDto extends User {
    private Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDetailsDto(String username, String password, Collection<? extends GrantedAuthority> authorities,Long id) {
        super(username, password, authorities);
        setId(id);
    }

}
