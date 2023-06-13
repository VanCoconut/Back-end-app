package com.lipari.app.users.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lipari.app.exception.AuthException;
import com.lipari.app.exception.DataException;
import com.lipari.app.users.responses.UserErrorResponse;

@RestControllerAdvice
public class UserRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> authExceptionHandler(AuthException exc){
		UserErrorResponse error = new UserErrorResponse();
		  error.setStatus(HttpStatus.BAD_REQUEST.value());
		  error.setMessage(exc.getMessage());
		  error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> authExceptionHandler(DataException exc){
		UserErrorResponse error = new UserErrorResponse();
		  error.setStatus(HttpStatus.UNAUTHORIZED.value());
		  error.setMessage(exc.getMessage());
		  error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
	}
	
	
	
	
	/*
	 * @ExceptionHandler(ValidationException.class)
	 * 
	 * @ResponseStatus(HttpStatus.BAD_REQUEST) public UserErrorResponse
	 * dataExceptionHandler(ValidationException ex) {
	 * 
	 * UserErrorResponse error = new UserErrorResponse();
	 * error.setStatus(HttpStatus.BAD_REQUEST.value());
	 * error.setMessage(ex.getMessage());
	 * error.setTimeStamp(System.currentTimeMillis());
	 * 
	 * return error; }
	 * 
	 * @ExceptionHandler(AuthException.class)
	 * 
	 * @ResponseStatus(HttpStatus.UNAUTHORIZED) public UserErrorResponse
	 * authExceptionHandler(AuthException ex) {
	 * 
	 * UserErrorResponse error = new UserErrorResponse();
	 * error.setStatus(HttpStatus.UNAUTHORIZED.value());
	 * error.setMessage(ex.getMessage());
	 * error.setTimeStamp(System.currentTimeMillis());
	 * 
	 * return error; }
	 */
}
