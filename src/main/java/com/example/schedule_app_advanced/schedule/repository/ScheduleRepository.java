package com.example.schedule_app_advanced.schedule.repository;

import com.example.schedule_app_advanced.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Schedule 엔티티에 대한 DB 접근을 담당하는 Repository 인터페이스
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
