package com.example.schedule.global.exception.valid;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {

    private String code; // 에러 코드
    private List<ValidationErrorDetail> errors;
}
