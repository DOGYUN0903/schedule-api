package com.example.schedule.global.exception.member;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends CustomException {

    public InvalidPasswordException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_PASSWORD";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}