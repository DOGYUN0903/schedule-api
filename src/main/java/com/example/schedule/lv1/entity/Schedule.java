package com.example.schedule.lv1.entity;

import com.example.schedule.lv1.dto.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedule_v1")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public Schedule(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public void update(ScheduleUpdateRequestDto updateRequestDto) {
        if (updateRequestDto.getWriter() != null) {
            this.writer = updateRequestDto.getWriter();
        }
        if (updateRequestDto.getTitle() != null) {
            this.title = updateRequestDto.getTitle();
        }
        if (updateRequestDto.getContents() != null) {
            this.contents = updateRequestDto.getContents();
        }
    }
}
