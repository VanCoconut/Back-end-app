package com.lipari.app.commons.exception.controllers;

import com.lipari.app.commons.exception.entities.DataErrorResponse;
import com.lipari.app.commons.exception.utils.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorResponse {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public DataErrorResponse badCredentialExceptionHandler(BadCredentialsException ex) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return error;
    }

    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataErrorResponse dataExceptionHandler(DataException ex) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return error;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataErrorResponse validationExceptionHandler(ValidationException ex) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return error;
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataErrorResponse notFoundExceptionHandler(NotFoundException ex) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return error;
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataErrorResponse invalidDataExceptionHandler(InvalidDataException ex) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return error;
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DataErrorResponse alreadyExistsExceptionHandler(AlreadyExistsException ex) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return error;
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public DataErrorResponse authExceptionHandler(AuthException exc) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataErrorResponse genericExceptionHandler(Exception ex) {

        DataErrorResponse error = new DataErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ex.printStackTrace();
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return error;
    }

}
