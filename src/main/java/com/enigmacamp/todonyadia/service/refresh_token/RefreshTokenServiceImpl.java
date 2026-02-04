package com.enigmacamp.todonyadia.service.refresh_token;

import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.entities.RefreshToken;
import com.enigmacamp.todonyadia.repository.MemberRepository;
import com.enigmacamp.todonyadia.repository.RefreshTokenRepository;
import com.enigmacamp.todonyadia.service.member.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

    @Value("${jwt.refresh.token.expiration}")
    private Long refreshExpired;

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, MemberService memberService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.memberService = memberService;
    }

    @Override
    public RefreshToken createRefreshToken(UUID memberId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setMember(memberService.getMemberEntityById(memberId));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpired(Instant.now().plusMillis(refreshExpired));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Boolean isRefreshTokenExpired(RefreshToken token) {
        return token.getExpired().isBefore(Instant.now());
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(
            () -> new RuntimeException("Refresh token not found")
        );
    }

    @Override
    public void deleteByMember(Member member) {
        refreshTokenRepository.deleteByMember(member);
    }
}
