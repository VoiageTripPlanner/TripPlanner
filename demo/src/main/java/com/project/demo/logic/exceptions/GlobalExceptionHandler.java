package com.project.demo.logic.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<ErrorResponse> handleRepositoryException(RepositoryException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(EmailServiceException.class)
    public ResponseEntity<ErrorResponse> handleEmailServiceException(EmailServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(FlightServiceException.class)
    public ResponseEntity<ErrorResponse> handleFlightServiceException(FlightServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(LocationServiceException.class)
    public ResponseEntity<ErrorResponse> handleLocationServiceException(LocationServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(LodgeServiceException.class)
    public ResponseEntity<ErrorResponse> handleLodgeServiceException(LodgeServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorResponse> handleUserServiceException(UserServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(CountryServiceException.class)
    public ResponseEntity<ErrorResponse> handleCountryServiceException(CountryServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(TripServiceException.class)
    public ResponseEntity<ErrorResponse> handleTripServiceException(CountryServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase(),
                e.getUserMessage(),
                e.getTimestamp(),
                e.getErrorType()
        );
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }



    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");

            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return errorDetail;
    }

}
