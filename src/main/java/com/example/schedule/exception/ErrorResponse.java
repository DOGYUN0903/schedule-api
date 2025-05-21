package com.example.schedule.exception;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ErrorResponse {

    private String code; // 에러 코드
    private List<ErrorDetail> errors;
}
