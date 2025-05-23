package com.example.schedule.global.exception.schedule;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends CustomException {

    public ScheduleNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "SCHEDULE_NOT_FOUND";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
