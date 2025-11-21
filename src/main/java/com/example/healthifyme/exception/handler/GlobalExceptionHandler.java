package com.example.healthifyme.exception.handler;

import com.example.healthifyme.DTO.response.ErrorResponse;
import com.example.healthifyme.exception.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e,
            HttpServletRequest httpServletRequest) {
        log.warn("Authentication failed with ex: {}", e.getMessage());

        ErrorResponse errorResponse =
            ErrorResponse.of(HttpStatus.UNAUTHORIZED, "Invalid email or password", httpServletRequest.getRequestURI());
        return errorResponse.toResponseEntity();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e,
            HttpServletRequest request) {
        log.warn("User already exists ex: {}", e.getMessage());

        ErrorResponse error = ErrorResponse.of(HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI());
        return error.toResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
            BindingResult bindingResult) {
        log.warn("Validation failed with ex: {}", e.getMessage());
        log.debug("Validation failed with field errors: {}", bindingResult.getFieldErrors());

        ErrorResponse errorResponse =
            ErrorResponse.of(HttpStatus.BAD_REQUEST, "Validation error", bindingResult.getFieldErrors());
        return errorResponse.toResponseEntity();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e,
            HttpServletRequest httpServletRequest) {
        log.error("Runtime exception occurred", e);

        ErrorResponse error =
            ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), httpServletRequest.getRequestURI());
        return error.toResponseEntity();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest httpServletRequest) {
        log.error("General exception occurred", e);

        ErrorResponse error = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred",
            httpServletRequest.getRequestURI());
        return error.toResponseEntity();
    }
}
