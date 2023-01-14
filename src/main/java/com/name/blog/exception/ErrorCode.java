package com.name.blog.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHENTICATION_FAILED(401, "AUTH001", " AUTHENTICATION_FAILED."),
    SIGNIN_FAILED(401, "AUTH002", " SIGNIN_FAILED."),
    SIGNUP_FAILED(401, "AUTH003", " SIGNUP_FAILED."),
    INVALID_JWT_TOKEN(401, "AUTH004", "INVALID_JWT_TOKEN.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}