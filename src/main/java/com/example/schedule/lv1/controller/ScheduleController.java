package com.example.schedule.lv1.controller;

import com.example.schedule.lv1.dto.ScheduleRequestDto;
import com.example.schedule.lv1.dto.ScheduleResponseDto;
import com.example.schedule.lv1.dto.ScheduleUpdateRequestDto;
import com.example.schedule.lv1.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(
                scheduleService.createSchedule(requestDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return new ResponseEntity<>(
                scheduleService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable("id") Long id,
                                               @Valid @RequestBody ScheduleUpdateRequestDto updateRequestDto) {
        scheduleService.updateSchedule(id, updateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
