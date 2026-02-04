package com.enigmacamp.todonyadia.service.refresh_token;

import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.entities.RefreshToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(UUID memberId);
    Boolean isRefreshTokenExpired(RefreshToken token);
    RefreshToken findByToken(String token);
    void deleteByMember(Member member);
}
