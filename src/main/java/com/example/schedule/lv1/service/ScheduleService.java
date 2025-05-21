package com.example.schedule.lv1.service;

import com.example.schedule.lv1.dto.ScheduleRequestDto;
import com.example.schedule.lv1.dto.ScheduleResponseDto;
import com.example.schedule.lv1.entity.Schedule;
import com.example.schedule.lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id: " + id);
        }

        Schedule findSchedule = optionalSchedule.get();

        return new ScheduleResponseDto(findSchedule);
    }

    public ScheduleResponseDto updateAllById(Long id) {
        scheduleRepository.findById(id);
        return null;
    }
}
