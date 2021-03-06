package com.mayi.transfer.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends RuntimeException{

    private int code;

    private HttpStatus httpStatus;

    public BusinessException(final ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.code = errorCodes.getCode();
        this.httpStatus = errorCodes.getHttpStatus();
    }
}
