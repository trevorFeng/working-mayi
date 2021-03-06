package com.mayi.transfer.controller;

import com.google.common.collect.ImmutableList;
import com.mayi.transfer.exception.BusinessException;
import com.mayi.transfer.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<List<ErrorResponse>> handleBusinessException(BusinessException e) {
        log.error("global BusinessException,code: {}", e.getCode(), e);
        final ErrorResponse errorResponse = new ErrorResponse(e.getCode(), "", e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(ImmutableList.of(errorResponse));
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ErrorResponse>> handleValidationException(MethodArgumentNotValidException e) {
        final List<ErrorResponse> errors = e.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    final String fieldName = ((FieldError) error).getField();
                    final String errorMessage = error.getDefaultMessage();
                    return new ErrorResponse(fieldName, errorMessage);
                })
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }




}
