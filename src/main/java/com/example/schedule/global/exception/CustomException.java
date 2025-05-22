package com.example.schedule.global.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public abstract String getCode(); // 에러 코드

    public abstract HttpStatus getStatus(); // 상태 코드
}
