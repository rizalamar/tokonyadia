package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.dto.request.LoginRequest;
import com.enigmacamp.todonyadia.dto.request.RefreshTokenRequest;
import com.enigmacamp.todonyadia.dto.request.RegisterRequest;
import com.enigmacamp.todonyadia.dto.response.LoginResponse;
import com.enigmacamp.todonyadia.dto.response.RefreshTokenResponse;
import com.enigmacamp.todonyadia.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        // System.out.println("Bcript: " + new BCryptPasswordEncoder().encode("downway"));
        return authService.login(loginRequest);
    }

    @GetMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("Register Successfully");
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }
}
