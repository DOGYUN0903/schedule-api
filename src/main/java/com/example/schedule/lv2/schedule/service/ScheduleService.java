package com.example.schedule.lv2.schedule.service;

import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.member.repository.MemberRepository;
import com.example.schedule.lv2.schedule.dto.CreateScheduleRequestDto;
import com.example.schedule.lv2.schedule.dto.ScheduleResponseDto;
import com.example.schedule.lv2.schedule.entity.Schedule;
import com.example.schedule.lv2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto createSchedule(Long memberId, CreateScheduleRequestDto requestDto) {
        Member findMember = memberRepository.findByIdOrElseThrow(memberId);

        Schedule schedule = new Schedule(findMember, requestDto.getTitle(), requestDto.getContents());

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }
}
