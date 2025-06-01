package com.example.schedule_app_advanced.schedule.controller;

import com.example.schedule_app_advanced.schedule.dto.ScheduleRequestDto;
import com.example.schedule_app_advanced.schedule.dto.ScheduleResponseDto;
import com.example.schedule_app_advanced.schedule.service.ScheduleService;
import com.example.schedule_app_advanced.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 CRUD 기능을 제공하는 Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성
     * 로그인된 유저 정보를 세션에서 꺼내와 연관관계 설정
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@RequestBody ScheduleRequestDto requestDto,
                                                      HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return ResponseEntity.ok(scheduleService.createSchedule(requestDto, user));
    }

    /**
     * 전체 일정 조회
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAll() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    /**
     * 특정 일정 조회
     * 로그인된 유저 정보 필요
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> get(@PathVariable Long id, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return ResponseEntity.ok(scheduleService.getSchedule(id, user));
    }

    /**
     * 일정 수정
     * 로그인된 유저 정보 필요
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                                      @RequestBody ScheduleRequestDto requestDto,
                                                      HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto, user));
    }

    /**
     * 일정 삭제
     * 로그인된 유저 정보 필요
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        scheduleService.deleteSchedule(id, user);
        return ResponseEntity.ok().build();
    }
}
