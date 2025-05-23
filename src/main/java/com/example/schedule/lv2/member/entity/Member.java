package com.example.schedule.lv2.member.entity;

import com.example.schedule.global.entity.BaseEntity;
import com.example.schedule.lv2.comment.entity.Comment;
import com.example.schedule.lv2.member.dto.update.MemberUpdateRequestDto;
import com.example.schedule.lv2.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 유저명

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Member(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void update(MemberUpdateRequestDto updateRequestDto) {
        if (updateRequestDto.getUsername() != null) {
            this.username = updateRequestDto.getUsername();
        }
        if (updateRequestDto.getPassword() != null) {
            this.password = updateRequestDto.getPassword();
        }
    }
}
