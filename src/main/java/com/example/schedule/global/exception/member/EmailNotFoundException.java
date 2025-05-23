package com.example.schedule.global.exception.member;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends CustomException {
    public EmailNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "EMAIL_NOT_FOUND";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
