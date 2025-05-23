package com.example.schedule.lv2.comment.service;

import com.example.schedule.global.exception.member.MemberNotFoundException;
import com.example.schedule.global.exception.schedule.ScheduleNotFoundException;
import com.example.schedule.lv2.comment.dto.CommentResponseDto;
import com.example.schedule.lv2.comment.entity.Comment;
import com.example.schedule.lv2.comment.repostiory.CommentRepository;
import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.member.repository.MemberRepository;
import com.example.schedule.lv2.schedule.entity.Schedule;
import com.example.schedule.lv2.schedule.repository.ScheduleRepository;
import com.example.schedule.lv2.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public CommentResponseDto createComment(Long scheduleId, String content, Long loginMemberId) {

        // 작성자 조회
        Member findMember = memberRepository.findById(loginMemberId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        // 일정 조회
        Schedule findSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("존재하지 않는 일정입니다."));

        // 댓글 생성
        Comment comment = new Comment(content, findMember, findSchedule);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    public List<CommentResponseDto> findAllComments(Long scheduleId) {
        return commentRepository.findAllCommentsByScheduleId(scheduleId).stream()
                .map(CommentResponseDto::new)
                .toList();
    }
}
