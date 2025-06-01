package com.example.schedule_app_advanced.schedule.service;

import com.example.schedule_app_advanced.schedule.dto.ScheduleRequestDto;
import com.example.schedule_app_advanced.schedule.dto.ScheduleResponseDto;
import com.example.schedule_app_advanced.schedule.entity.Schedule;
import com.example.schedule_app_advanced.schedule.repository.ScheduleRepository;
import com.example.schedule_app_advanced.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@Getter
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, User user) {
        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContent(), user); // 연관된 유저를 함께 저장
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단건 조회
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = findSchedule(id);
        return new ScheduleResponseDto(schedule);
    }

    // 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        schedule.update(requestDto.getTitle(), requestDto.getContent());
        return new ScheduleResponseDto(schedule);
    }

    // 삭제
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
    }
}
