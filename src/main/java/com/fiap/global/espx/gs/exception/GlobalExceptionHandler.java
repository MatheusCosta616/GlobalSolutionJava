package com.fiap.global.espx.gs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException e) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> error = new HashMap<>();
        error.put("error_code", "RESOURCE_NOT_FOUND");
        error.put("error_description", e.getMessage());
        response.put("errors", Collections.singletonList(error));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> error = new HashMap<>();
        error.put("error_code", "INVALID_REQUEST");
        error.put("error_description", e.getMessage());
        response.put("errors", Collections.singletonList(error));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> error = new HashMap<>();
        error.put("error_code", "INTERNAL_SERVER_ERROR");
        error.put("error_description", "Ocorreu um erro interno no servidor");
        response.put("errors", Collections.singletonList(error));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
} 