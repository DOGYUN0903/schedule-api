package com.example.schedule.lv2.schedule.controller;

import com.example.schedule.lv2.schedule.dto.CreateScheduleRequestDto;
import com.example.schedule.lv2.schedule.dto.ScheduleResponseDto;
import com.example.schedule.lv2.schedule.dto.UpdateScheduleRequestDto;
import com.example.schedule.lv2.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findByMemberId(@PathVariable("memberId") Long memberId) {
        return new ResponseEntity<>(scheduleService.findByMemberId(memberId), HttpStatus.OK);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/schedules/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable("id") Long id,
                                               @Valid @RequestBody UpdateScheduleRequestDto requestDto) {
        scheduleService.updateSchedule(id, requestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") Long id,
                                               @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
