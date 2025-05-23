package com.example.schedule.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CustomErrorResponse {

    private String code; // 에러 코드 : EMAIL_NOT_FOUND
    private String message; // 에러 메시지 : "유효하지 않은 이메일입니다."
    private int status; // HTTP 상태 코드 : 404
    private String path; // 요청 URI : /api/v2/members
    private LocalDateTime errorTime; // 에러 발생 시각
}
