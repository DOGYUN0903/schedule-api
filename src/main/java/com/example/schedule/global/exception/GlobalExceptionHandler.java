package com.example.schedule.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<ErrorDetail> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new ErrorDetail(e.getField(), e.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(
                new ErrorResponse("VALIDATION_FAILED", errorList),
                HttpStatus.BAD_REQUEST
        );
    }
}
