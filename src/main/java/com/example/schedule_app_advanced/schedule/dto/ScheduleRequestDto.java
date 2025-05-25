// ScheduleRequestDto.java
package com.example.schedule_app_advanced.schedule.dto;

import lombok.Getter;

/**
 * 일정 생성 및 수정 요청을 위한 DTO
 */
@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;
}
