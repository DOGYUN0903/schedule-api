package com.example.schedule.lv2.schedule.dto;

import com.example.schedule.lv2.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private Long id;

    private String username; // 작성자

    private String title;

    private String contents;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getMember().getUsername();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }
}
