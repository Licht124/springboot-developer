package me.ksu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ksu.springbootdeveloper.domain.RefreshToken;
import me.ksu.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 리프레시 토큰 객체 검색 후 전달하는 메소드
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }

}
