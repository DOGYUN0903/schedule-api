package com.example.schedule.global.exception.comment;

import com.example.schedule.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends CustomException {

    public CommentNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "COMMENT_NOT_FOUND";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
