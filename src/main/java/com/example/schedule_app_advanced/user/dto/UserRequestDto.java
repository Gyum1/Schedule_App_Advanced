package com.example.schedule_app_advanced.user.dto;

import lombok.Getter;

/**
 * 유저 생성 및 수정 요청을 처리하기 위한 DTO
 */
@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password; // @TODO: Lv4에서 암호화 적용 예정
}
