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

    /**
     * 일정 생성
     */
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, User user) {
        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContent(), user);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 전체 일정 조회
     */
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 단건 조회 (로그인 유저 본인만 가능)
     */
    public ScheduleResponseDto getSchedule(Long id, User user) {
        Schedule schedule = findSchedule(id);
        validateUser(schedule, user);
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 수정 (로그인 유저 본인만 가능)
     */
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, User user) {
        Schedule schedule = findSchedule(id);
        validateUser(schedule, user);
        schedule.update(requestDto.getTitle(), requestDto.getContent());
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 삭제 (로그인 유저 본인만 가능)
     */
    public void deleteSchedule(Long id, User user) {
        Schedule schedule = findSchedule(id);
        validateUser(schedule, user);
        scheduleRepository.delete(schedule);
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
    }

    /**
     * 일정 작성자가 현재 로그인 유저와 일치하는지 확인
     */
    private void validateUser(Schedule schedule, User user) {
        if (!schedule.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 일정에 접근할 권한이 없습니다.");
        }
    }
}
