// ScheduleResponseDto.java
package com.example.schedule_app_advanced.schedule.dto;

import com.example.schedule_app_advanced.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 조회 응답을 위한 DTO
 */
@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
