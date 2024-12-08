package com.example.adf_project.controllers.handlers;

import com.example.adf_project.services.exceptions.BadDataException;
import com.example.adf_project.services.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(NotFoundException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<ApiError> handleBadDataException(BadDataException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleBadDataSentInRequest(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String errorMessage = fieldErrors.stream()
                .map(fieldError -> String.format("Field '%s': %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining(" | "));

        ApiError apiError = new ApiError(LocalDateTime.now(), errorMessage, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(LocalDateTime.now(), "The request would have created a conflict.", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}