package com.name.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomAuthenticationException.class)
    protected ResponseEntity<ExceptionResponse> handleCustomAuthenticationException(CustomAuthenticationException e) {
        log.info("handleCustomAuthenticationException", e);

        ExceptionResponse response = ExceptionResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignInFailedException.class)
    protected ResponseEntity<ExceptionResponse> handleSignInFailedException(SignInFailedException e) {
        log.info("handleSignInFailedException", e);

        ExceptionResponse response = ExceptionResponse.builder()
                .code(ErrorCode.SIGNIN_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.SIGNIN_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignUpFailedException.class)
    protected ResponseEntity<ExceptionResponse> handleSignUpFailedException(SignUpFailedException e) {
        log.info("handleSignUpFailedException", e);

        ExceptionResponse response = ExceptionResponse.builder()
                .code(ErrorCode.SIGNUP_FAILED.getCode())
                .message(e.getMessage())
                .status(ErrorCode.SIGNUP_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomJwtRuntimeException.class)
    protected ResponseEntity<ExceptionResponse> handleJwtException(CustomJwtRuntimeException e) {
        log.info("handleJwtException", e);

        ExceptionResponse response = ExceptionResponse.builder()
                .code(ErrorCode.INVALID_JWT_TOKEN.getCode())
                .message(ErrorCode.INVALID_JWT_TOKEN.getMessage())
                .status(ErrorCode.INVALID_JWT_TOKEN.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
