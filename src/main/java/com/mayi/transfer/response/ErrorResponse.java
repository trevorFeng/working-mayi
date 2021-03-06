package com.mayi.transfer.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private int code;
    private String field;
    private String message;

    public ErrorResponse(final int code) {
        this.code = code;
    }

    public ErrorResponse(final String fieldName, final String message) {
        this.field = fieldName;
        this.message = message;
    }

    public ErrorResponse(final int code, final String fieldName, final String message) {
        this.code = code;
        this.field = fieldName;
        this.message = message;
    }
}
