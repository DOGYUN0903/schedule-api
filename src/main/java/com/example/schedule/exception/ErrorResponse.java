package com.example.schedule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String code; // 에러 코드
    private List<ErrorDetail> errors;
}
