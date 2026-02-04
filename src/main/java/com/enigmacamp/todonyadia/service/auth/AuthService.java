package com.enigmacamp.todonyadia.service.auth;

import com.enigmacamp.todonyadia.dto.request.LoginRequest;
import com.enigmacamp.todonyadia.dto.request.RefreshTokenRequest;
import com.enigmacamp.todonyadia.dto.request.RegisterRequest;
import com.enigmacamp.todonyadia.dto.response.LoginResponse;
import com.enigmacamp.todonyadia.dto.response.RefreshTokenResponse;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
