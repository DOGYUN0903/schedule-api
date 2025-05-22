package com.example.schedule.global.exception.member;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends CustomException {

    public InvalidPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
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
