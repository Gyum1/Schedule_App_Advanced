package com.example.schedule_app_advanced.schedule.controller;

import com.example.schedule_app_advanced.schedule.dto.ScheduleRequestDto;
import com.example.schedule_app_advanced.schedule.dto.ScheduleResponseDto;
import com.example.schedule_app_advanced.schedule.service.ScheduleService;
import com.example.schedule_app_advanced.user.entity.User;
import com.example.schedule_app_advanced.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@RequestBody ScheduleRequestDto requestDto) {
        // 현재는 임시로 userId = 1L 유저를 가져와 연관관계 설정
        // @TODO: 로그인 유저 정보로 교체 예정
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return ResponseEntity.ok(scheduleService.createSchedule(requestDto, user));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAll() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                                      @RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
