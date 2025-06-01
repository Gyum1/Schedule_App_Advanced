package com.example.schedule_app_advanced.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저 생성 및 수정 요청을 처리하기 위한 DTO
 */
@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String email;

    // @TODO: Lv3에서 비밀번호 필드 추가 예정
}
