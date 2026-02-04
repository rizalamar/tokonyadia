package com.enigmacamp.todonyadia.config;

import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.entities.Role;
import com.enigmacamp.todonyadia.repository.MemberRepository;
import com.enigmacamp.todonyadia.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    @Value("${app.admin.username}")
    private String usernameAdmin;

    @Value("${app.admin.password}")
    private String passwordAdmin;

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin(){
        if(memberRepository.existsByUsername(usernameAdmin)) return;

        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow(
                () -> new RuntimeException("Role ADMIN not found")
        );

        Member admin = new Member();
        admin.setUsername(usernameAdmin);
        admin.setPassword(passwordEncoder.encode(passwordAdmin));
        admin.setRoleSet(Set.of(adminRole));
        memberRepository.save(admin);
    }
}
