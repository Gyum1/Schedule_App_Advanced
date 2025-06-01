package com.example.schedule_app_advanced.comment.service;

import com.example.schedule_app_advanced.comment.dto.CommentRequestDto;
import com.example.schedule_app_advanced.comment.dto.CommentResponseDto;
import com.example.schedule_app_advanced.comment.entity.Comment;
import com.example.schedule_app_advanced.comment.repository.CommentRepository;
import com.example.schedule_app_advanced.schedule.entity.Schedule;
import com.example.schedule_app_advanced.schedule.repository.ScheduleRepository;
import com.example.schedule_app_advanced.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 댓글 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * 댓글 생성
     * @param scheduleId 대상 일정 ID
     * @param requestDto 댓글 요청 데이터
     * @param user 로그인한 사용자
     * @return 생성된 댓글 응답 정보
     */
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, User user) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        Comment comment = new Comment(requestDto.getContent(), user, schedule);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    /**
     * 특정 일정의 모든 댓글 조회
     */
    public List<CommentResponseDto> getComments(Long scheduleId) {
        return commentRepository.findAllByScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 댓글 수정
     */
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = findComment(commentId);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        comment.update(requestDto.getContent());
        return new CommentResponseDto(comment);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long commentId, User user) {
        Comment comment = findComment(commentId);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
    }
}
