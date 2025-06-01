package com.example.schedule_app_advanced.user.service;

import com.example.schedule_app_advanced.config.PasswordEncoder;
import com.example.schedule_app_advanced.user.dto.UserRequestDto;
import com.example.schedule_app_advanced.user.dto.UserResponseDto;
import com.example.schedule_app_advanced.user.entity.User;
import com.example.schedule_app_advanced.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 처리
     * @param requestDto 사용자 등록 요청 정보
     * @return 생성된 사용자 응답 정보
     */
    public UserResponseDto signup(UserRequestDto requestDto) {
        // 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), encodedPassword);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    /**
     * 로그인 처리
     * @param requestDto 로그인 요청 정보
     * @param req HttpServletRequest
     */
    public void login(UserRequestDto requestDto, HttpServletRequest req) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일을 찾을 수 없습니다."));

        // 암호화된 비밀번호 비교
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        req.getSession().setAttribute("user", user);
    }

    /**
     * 전체 사용자 조회
     */
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 특정 사용자 조회
     */
    public UserResponseDto getUser(Long id) {
        User user = findUser(id);
        return new UserResponseDto(user);
    }

    /**
     * 사용자 정보 수정
     */
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = findUser(id);
        user.update(requestDto.getUsername(), requestDto.getEmail());
        return new UserResponseDto(user);
    }

    /**
     * 사용자 삭제
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }
}
