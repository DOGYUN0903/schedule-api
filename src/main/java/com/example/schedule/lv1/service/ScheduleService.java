package com.example.schedule.lv1.service;

import com.example.schedule.lv1.dto.ScheduleRequestDto;
import com.example.schedule.lv1.dto.ScheduleResponseDto;
import com.example.schedule.lv1.dto.ScheduleUpdateRequestDto;
import com.example.schedule.lv1.entity.Schedule;
import com.example.schedule.lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule =
                new Schedule(
                        requestDto.getWriter(),
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

    public ScheduleResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public void updateSchedule(Long id, ScheduleUpdateRequestDto updateRequestDto) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.update(updateRequestDto);
    }

    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(schedule);
    }
}
