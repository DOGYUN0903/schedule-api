package com.example.schedule.lv2.member.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;

    private String username;

    private String email;
}
