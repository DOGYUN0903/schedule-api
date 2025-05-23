package com.example.schedule.lv2.schedule.entity;

import com.example.schedule.global.entity.BaseEntity;
import com.example.schedule.lv2.comment.entity.Comment;
import com.example.schedule.lv2.member.dto.update.MemberUpdateRequestDto;
import com.example.schedule.lv2.member.entity.Member;
import com.example.schedule.lv2.schedule.dto.UpdateScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "schedule_v2")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule(Member member, String title, String contents) {
        this.member = member;
        this.title = title;
        this.contents = contents;
    }

    public void update(UpdateScheduleRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContents() != null) {
            this.contents = requestDto.getContents();
        }
    }

}
