package com.example.schedule_app_advanced.user.service;

import com.example.schedule_app_advanced.user.dto.UserRequestDto;
import com.example.schedule_app_advanced.user.dto.UserResponseDto;
import com.example.schedule_app_advanced.user.entity.User;
import com.example.schedule_app_advanced.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자 관련 비즈니스 로직 처리 클래스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입 (Lv3: 비밀번호 필드 포함, 암호화는 아직 미적용)
     */
    public UserResponseDto signup(UserRequestDto requestDto) {
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        userRepository.save(user);
        return new UserResponseDto(user);
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
     * 단건 사용자 조회
     */
    public UserResponseDto getUser(Long id) {
        return new UserResponseDto(findUser(id));
    }

    /**
     * 사용자 정보 수정
     */
    @Transactional
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

    /**
     * ID 기반 사용자 조회 (없을 시 예외 발생)
     */
    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
    }
}
