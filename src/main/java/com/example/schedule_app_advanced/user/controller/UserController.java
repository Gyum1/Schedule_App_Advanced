package com.example.schedule_app_advanced.user.controller;

import com.example.schedule_app_advanced.user.dto.UserRequestDto;
import com.example.schedule_app_advanced.user.dto.UserResponseDto;
import com.example.schedule_app_advanced.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 CRUD 기능을 제공하는 Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param requestDto 사용자 등록 요청 정보
     * @return 생성된 사용자 응답 정보
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto requestDto, HttpServletRequest req) {
        try {
            userService.login(requestDto, req);
            return ResponseEntity.ok("로그인 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("로그인 실패: " + e.getMessage());
        }
    }

    /**
     * 전체 사용자 조회
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * 특정 사용자 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    /**
     * 사용자 정보 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,
                                                  @Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto));
    }

    /**
     * 사용자 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
