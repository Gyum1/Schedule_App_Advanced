package com.example.schedule_app_advanced.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 유저 생성 및 수정 요청을 처리하기 위한 DTO
 */
@Getter
public class UserRequestDto {

    @NotBlank(message = "유저명은 필수입니다.")
    @Size(max = 4, message = "유저명은 최대 4자까지 가능합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String password;
}
