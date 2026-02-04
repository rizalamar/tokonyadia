package com.enigmacamp.todonyadia.service.auth;

import com.enigmacamp.todonyadia.dto.request.LoginRequest;
import com.enigmacamp.todonyadia.dto.request.RegisterRequest;
import com.enigmacamp.todonyadia.dto.response.LoginResponse;
import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.entities.Role;
import com.enigmacamp.todonyadia.security.jwt.JWTUtil;
import com.enigmacamp.todonyadia.service.customer.CustomerService;
import com.enigmacamp.todonyadia.service.member.MemberService;
import com.enigmacamp.todonyadia.service.role.RoleService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final MemberService memberService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RoleService roleService;

    public AuthServiceImpl(MemberService memberService, CustomerService customerService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTUtil jwtUtil, RoleService roleService) {
        this.memberService = memberService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleService = roleService;
    }

    @Override
    public void register(RegisterRequest registerRequest) {

        if(memberService.findByUsername(registerRequest.getUsername())){
            throw new IllegalStateException("Username already exist");
        }

        Role userRole = roleService.findByName("USER").orElseThrow(
                () -> new RuntimeException("Role USER not found")
        );

        Member member = new Member();
        member.setUsername(registerRequest.getUsername());
        member.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        member.setRoleSet(Set.of(userRole));
        memberService.saveMemberEntity(member);

        Customer customer = new Customer();
        customer.setFullname(registerRequest.getFullname());
        customer.setAddress(registerRequest.getAddress());
        customer.setEmail(registerRequest.getEmail());
        customer.setGender(registerRequest.getGender());
        customerService.saveCustomerEntity(customer);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.username(), loginRequest.password()
                ));
        String token = jwtUtil.generateToken(authentication);
        return new LoginResponse(token);
    }
}
