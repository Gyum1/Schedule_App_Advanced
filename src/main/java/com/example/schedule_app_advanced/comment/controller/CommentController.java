package com.example.schedule_app_advanced.comment.controller;

import com.example.schedule_app_advanced.comment.dto.CommentRequestDto;
import com.example.schedule_app_advanced.comment.dto.CommentResponseDto;
import com.example.schedule_app_advanced.comment.service.CommentService;
import com.example.schedule_app_advanced.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 CRUD 기능을 제공하는 Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성
     */
    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentResponseDto> create(@PathVariable Long scheduleId,
                                                     @RequestBody CommentRequestDto requestDto,
                                                     HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return ResponseEntity.ok(commentService.createComment(scheduleId, requestDto, user));
    }

    /**
     * 특정 일정의 댓글 전체 조회
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getAll(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getComments(scheduleId));
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long commentId,
                                                     @RequestBody CommentRequestDto requestDto,
                                                     HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return ResponseEntity.ok(commentService.updateComment(commentId, requestDto, user));
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId,
                                       HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        commentService.deleteComment(commentId, user);
        return ResponseEntity.ok().build();
    }
}
