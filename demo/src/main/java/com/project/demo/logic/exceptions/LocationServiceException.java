package com.project.demo.logic.exceptions;

import org.springframework.http.HttpStatus;

public class LocationServiceException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorType;
    private final String userMessage;
    private final String timestamp;
    private final Throwable cause;

    public LocationServiceException(String message, HttpStatus httpStatus, String errorType, String userMessage, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.errorType = errorType;
        this.userMessage = userMessage;
        this.timestamp = java.time.Instant.now().toString();
        this.cause = cause;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
