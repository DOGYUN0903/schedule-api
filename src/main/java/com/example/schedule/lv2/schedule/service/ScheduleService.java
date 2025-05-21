package com.example.schedule.lv2.schedule.service;

import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.member.repository.MemberRepository;
import com.example.schedule.lv2.schedule.dto.CreateScheduleRequestDto;
import com.example.schedule.lv2.schedule.dto.ScheduleResponseDto;
import com.example.schedule.lv2.schedule.dto.UpdateScheduleRequestDto;
import com.example.schedule.lv2.schedule.entity.Schedule;
import com.example.schedule.lv2.schedule.repository.ScheduleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAllWithMember().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public List<ScheduleResponseDto> findByMemberId(Long memberId) {
        Member findMember = memberRepository.findByIdOrElseThrow(memberId);

        return scheduleRepository.findByMemberId(findMember.getId()).stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public void updateSchedule(Long id, UpdateScheduleRequestDto requestDto, String password) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if (!findSchedule.getMember().getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findSchedule.update(requestDto);
    }

    public void deleteSchedule(Long id, String password) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if (!findSchedule.getMember().getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(findSchedule);
    }
}
