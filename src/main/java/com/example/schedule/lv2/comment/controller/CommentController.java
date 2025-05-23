package com.example.schedule.lv2.comment.controller;

import com.example.schedule.global.session.SessionConst;
import com.example.schedule.lv2.comment.dto.CommentRequestDto;
import com.example.schedule.lv2.comment.dto.CommentResponseDto;
import com.example.schedule.lv2.comment.dto.UpdateCommentRequestDto;
import com.example.schedule.lv2.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("scheduleId") Long scheduleId,
                                                            @Valid @RequestBody CommentRequestDto requestDto,
                                                            HttpServletRequest request) {
        Long loginMemberId = (Long) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        return new ResponseEntity<>(commentService.createComment
                (
                        scheduleId,
                        requestDto.getContent(),
                        loginMemberId
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAllComments(@PathVariable("scheduleId") Long scheduleId) {
        return new ResponseEntity<>(commentService.findAllComments(scheduleId), HttpStatus.OK);
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") Long commentId,
                                              @Valid @RequestBody UpdateCommentRequestDto requestDto,
                                              HttpServletRequest request) {

        Long loginMemberId = (Long) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        commentService.updateComment(commentId, requestDto.getContent(), loginMemberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commendId") Long commentId,
                                              HttpServletRequest request) {
        Long loginMemberId = (Long) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        commentService.deleteComment(commentId, loginMemberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
