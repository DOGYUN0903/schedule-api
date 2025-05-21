package com.example.schedule.lv1.dto;

import com.example.schedule.lv1.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long id;

    private String writer;

    private String title;

    private String contents;

    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.writer = schedule.getWriter();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
