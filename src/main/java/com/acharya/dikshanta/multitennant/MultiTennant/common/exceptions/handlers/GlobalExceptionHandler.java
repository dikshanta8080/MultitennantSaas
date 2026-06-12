package com.acharya.dikshanta.multitennant.MultiTennant.common.exceptions.handlers;

import com.acharya.dikshanta.multitennant.MultiTennant.common.dto.request.ApiResponse;
import com.acharya.dikshanta.multitennant.MultiTennant.common.exceptions.BusinessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllExceptions(Exception e) {

        int statusCode = e instanceof BusinessException ? 400 : 500;

        return ResponseEntity.status(statusCode)
                .body(
                        ApiResponse.<Void>builder()
                                .message(e.getMessage())
                                .statusCode(statusCode)
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        Map<String, String> errorMap = fieldErrors.stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));

        ApiResponse<Map<String, String>> apiResponse = ApiResponse.<Map<String, String>>builder()
                .message("Validation failed")
                .statusCode(400)
                .data(errorMap)
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }
}