package com.example.schedule.lv2.member.dto.signup;

import com.example.schedule.lv2.member.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberSignupResponseDto {

    private Long id;

    private String username;

    private String email;

    private LocalDateTime modifiedAt;

    public MemberSignupResponseDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.modifiedAt = member.getModifiedAt();
    }
}
