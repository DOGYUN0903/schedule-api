package com.example.schedule.lv2.schedule.controller;

import com.example.schedule.lv2.schedule.dto.CreateScheduleRequestDto;
import com.example.schedule.lv2.schedule.dto.ScheduleResponseDto;
import com.example.schedule.lv2.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/members/{memberId}/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@PathVariable("memberId") Long memberId,
                                                              @Valid @RequestBody CreateScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.createSchedule(memberId, requestDto), HttpStatus.CREATED);
    }
}
