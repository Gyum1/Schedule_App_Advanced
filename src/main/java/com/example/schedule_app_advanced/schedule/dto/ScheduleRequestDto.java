package com.example.schedule_app_advanced.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 일정 생성 및 수정 요청을 위한 DTO
 */
@Getter
public class ScheduleRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 10, message = "제목은 최대 10자까지 가능합니다.")
    private String title;

    @Size(max = 255, message = "내용은 최대 255자까지 가능합니다.")
    private String content;
}
