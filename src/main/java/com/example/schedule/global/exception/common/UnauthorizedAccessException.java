package com.example.schedule.global.exception.common;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends CustomException {

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "UNAUTHORIZED_ACCESS";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
