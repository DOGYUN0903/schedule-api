package com.example.schedule.global.exception.member;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends CustomException {

    public MemberNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "MEMBER_NOT_FOUND";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
