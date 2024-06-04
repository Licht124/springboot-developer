package me.ksu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ksu.springbootdeveloper.config.jwt.TokenProvider;
import me.ksu.springbootdeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
// 전달받은 리프레시 토큰으로 유효성 검사 진행
// 유효 토큰일 경우 리프레시 토큰으로 사용자 id 조회
// 마지막으로 새로운 액세스 토큰 생성
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));

    }
}
