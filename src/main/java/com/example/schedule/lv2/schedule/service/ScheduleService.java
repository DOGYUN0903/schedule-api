package com.example.schedule.lv2.schedule.service;

import com.example.schedule.global.exception.common.InvalidPasswordException;
import com.example.schedule.global.exception.common.UnauthorizedAccessException;
import com.example.schedule.global.exception.member.MemberNotFoundException;
import com.example.schedule.global.exception.schedule.ScheduleNotFoundException;
import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.member.repository.MemberRepository;
import com.example.schedule.lv2.schedule.dto.CreateScheduleRequestDto;
import com.example.schedule.lv2.schedule.dto.ScheduleResponseDto;
import com.example.schedule.lv2.schedule.dto.UpdateScheduleRequestDto;
import com.example.schedule.lv2.schedule.entity.Schedule;
import com.example.schedule.lv2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto createSchedule(Long memberId, CreateScheduleRequestDto requestDto) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        Schedule schedule = new Schedule(findMember, requestDto.getTitle(), requestDto.getContents());

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAllWithMember().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public List<ScheduleResponseDto> findByMemberId(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        return scheduleRepository.findByMemberId(findMember.getId()).stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("존재하지 않는 일정입니다."));
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public void updateSchedule(Long id, Long loginMemberId, UpdateScheduleRequestDto requestDto) {
        Schedule findSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("존재하지 않는 일정입니다."));

        if (!findSchedule.getMember().getId().equals(loginMemberId)) {
            throw new UnauthorizedAccessException("작성자만 일정을 수정할 수 있습니다.");
        }

        findSchedule.update(requestDto);
    }

    public void deleteSchedule(Long id, Long loginMemberId) {
        Schedule findSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("존재하지 않는 일정입니다."));

        if (!findSchedule.getMember().getId().equals(loginMemberId)) {
            throw new UnauthorizedAccessException("작성자만 일정을 삭제할 수 있습니다.");
        }

        scheduleRepository.delete(findSchedule);
    }
}
