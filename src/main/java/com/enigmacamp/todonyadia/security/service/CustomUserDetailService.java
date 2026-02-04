package com.enigmacamp.todonyadia.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("admin")){
            throw new UsernameNotFoundException("Username not found");
        }

        return User.builder()
            .username("admin")
            .password("$2a$10$nW7RQmKu/gr.3eP1rdZ7L.3RVaTbpDjYgYId4Y7MiYZPgxy5RAPki")
            .roles("ADMIN")
            .build();
    }
}
