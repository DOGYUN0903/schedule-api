package com.example.schedule.global.exception;

import com.example.schedule.global.exception.valid.ValidationErrorDetail;
import com.example.schedule.global.exception.valid.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<ValidationErrorDetail> errorList =
                ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new ValidationErrorDetail(e.getField(), e.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(
                new ValidationErrorResponse("VALIDATION_FAILED", errorList),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomException(CustomException e,
                                                                     HttpServletRequest request) {

        CustomErrorResponse errorResponse =
                new CustomErrorResponse(
                        e.getCode(),
                        e.getMessage(),
                        e.getStatus().value(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(errorResponse, e.getStatus());
    }
}
