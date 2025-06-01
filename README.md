# 🗓️ Schedule App Advanced

## 1. 개발 환경

- Java 17
- Spring Boot 3.5.0
- Gradle
- JPA (Hibernate)
- MySQL 8
- IntelliJ IDEA
- Postman / HTTP Client (for API testing)
- Lombok
- Jakarta Servlet
- BCrypt (비밀번호 암호화)
- Git / GitHub

---

## 2. 프로젝트 목표

- 일정 관리 웹 서비스 개발
- 사용자 인증 기반으로 일정 CRUD 구현
- 쿠키/세션 기반 로그인 시스템 직접 구현 (Spring Security 미사용)
- JPA 기반의 도메인 모델 설계 및 연관관계 매핑 경험
- 실무와 유사한 구조로 API 명세, 예외처리, Validation, 비밀번호 암호화 적용

---

## 3. 주요 기능

###  유저 기능
- 회원가입 (Validation 적용)
- 로그인 (세션 기반 인증 처리)
- 로그아웃
- 내 정보 조회
- 비밀번호 암호화 (BCrypt)

###  일정 기능
- 로그인한 유저 기준 일정 등록/조회/수정/삭제
- 작성자만 수정 및 삭제 가능
- JPA Auditing을 활용한 생성일/수정일 관리

###  댓글 기능
- 로그인 유저가 일정에 댓글 등록/조회/수정/삭제 가능
- 댓글도 작성자만 수정 및 삭제 가능
- 일정-댓글, 유저-댓글 연관관계 매핑

---

## 4. API 명세

### 일정 API

| 기능 | Method | URL | 요청 값 | 응답 값 | 상태코드 |
| --- | --- | --- | --- | --- | --- |
| 일정 등록 | POST | /api/schedules | { "title": "string", "content": "string" } | { "id": 1, "title": "...", ... } | 200 |
| 일정 조회(단건) | GET | /api/schedules/{scheduleId} | PathVariable | { "id": 1, "title": "...", ... } | 200 |
| 일정 목록 조회 | GET | /api/schedules | 없음 | [ { "id": 1, "title": "...", ... }, ... ] | 200 |
| 일정 수정 | PUT | /api/schedules/{scheduleId} | { "title": "수정 제목", "content": "수정 내용" } | 수정된 일정 정보 | 200 |
| 일정 삭제 | DELETE | /api/schedules/{scheduleId} | PathVariable | 없음 | 200 |

---

### 유저 API

| 기능 | Method | URL | 요청 값 | 응답 값                                           | 상태코드 |
| --- | --- | --- | --- |------------------------------------------------| --- |
| 회원가입 | POST | /api/users/signup | { "username": "사용자", "email": "user@email.com", "password": "1234" } | 회원가입 완료 메시지                                    | 200 |
| 로그인 | POST | /api/users/login | { "email": "...", "password": "..." } | 세션 쿠키 + 로그인 성공 메시지                             | 200 |
| 로그아웃 | POST | /api/users/logout | 없음 | 로그아웃 완료 메시지                                    | 200 |
| 내 정보 조회 | GET | /api/users/me | 없음 | { "id": 1, "username": "...", "email": "..." } | 200 |

---

## 5. ERD 설계도

- 유저-일정: 1:N 단방향
- 유저-댓글: 1:N 단방향
- 일정-댓글: 1:N 단방향

![ERD](./docs/erd.png)
![ERD](./docs/erd(comments).png)

---

##  6. 인프라 구조
(단일 서버 환경)

