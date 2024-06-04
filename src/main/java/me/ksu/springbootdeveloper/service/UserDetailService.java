package me.ksu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ksu.springbootdeveloper.domain.User;
import me.ksu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    //사용자 이름(email)로 사용자 정보 load
    // 로그인 폼 제출 시 인증 요청이 발생하고
    // 인증 필터가 요청을 가로챔
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));

    }

}
