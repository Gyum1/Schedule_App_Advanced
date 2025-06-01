package com.example.schedule_app_advanced.user.dto;

import com.example.schedule_app_advanced.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 유저 정보를 응답하기 위한 DTO
 */
@Getter
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
