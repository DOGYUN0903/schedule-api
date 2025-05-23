package com.example.schedule.lv2.comment.controller;

import com.example.schedule.global.session.SessionConst;
import com.example.schedule.lv2.comment.dto.CommentRequestDto;
import com.example.schedule.lv2.comment.dto.CommentResponseDto;
import com.example.schedule.lv2.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
