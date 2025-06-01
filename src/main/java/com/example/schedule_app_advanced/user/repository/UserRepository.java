package com.example.schedule_app_advanced.user.repository;

import com.example.schedule_app_advanced.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User 엔티티에 대한 DB 접근을 담당하는 Repository 인터페이스
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
