package com.example.schedule_app_advanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring Boot 애플리케이션의 시작점
 */
@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 기능 활성화
public class ScheduleAppAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleAppAdvancedApplication.class, args);
    }
}
