package com.example.schedule.global.exception.member;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends CustomException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "EMAIL_ALREADY_EXISTS";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
