package com.mayi.transfer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {

    TOKEN_NOT_FOUND(401001, "token is not found", HttpStatus.UNAUTHORIZED),
    TOKEN_ERROR(401002, "token is error", HttpStatus.UNAUTHORIZED),


    CLIENT_ID_NOT_FOUND(404001, "clientId is not found", HttpStatus.NOT_FOUND),
    PAYMENT_ACCOUNT_NOT_FOUND(404002, "payment account is not found", HttpStatus.NOT_FOUND),


    BALANCE_NOT_ENOUGH(500001, "balance not enough", HttpStatus.INTERNAL_SERVER_ERROR),
    BALANCE_UPDATE_FAILED(500002, "balance update failed", HttpStatus.INTERNAL_SERVER_ERROR),
    TRANSFER_AMOUNT_ILLEGAL(500003, "com.mayi.transfer amount illegal", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;


    ErrorCodes(final int code, final String message, final HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
