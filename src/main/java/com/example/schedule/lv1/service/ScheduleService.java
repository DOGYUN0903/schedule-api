package com.example.schedule.lv1.service;

import com.example.schedule.lv1.dto.ScheduleRequestDto;
import com.example.schedule.lv1.dto.ScheduleResponseDto;
import com.example.schedule.lv1.entity.Schedule;
import com.example.schedule.lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule =
                new Schedule(
                        requestDto.getTitle(),
                        requestDto.getContents()
                );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }
}
