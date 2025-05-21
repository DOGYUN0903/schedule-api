package com.example.schedule.lv2.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteMemberRequestDto {

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
