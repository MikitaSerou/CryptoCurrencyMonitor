package com.serov.cryptocurrencymonitor.controller;

import com.serov.cryptocurrencymonitor.exception.CurrencyException;
import com.serov.cryptocurrencymonitor.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(CurrencyException.class)
    public ResponseEntity<ErrorResponse> handleCurrencyException(CurrencyException ex) {
        ErrorResponse response = new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> mismatchArgument(MethodArgumentTypeMismatchException ex) {
        ErrorResponse response = new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
