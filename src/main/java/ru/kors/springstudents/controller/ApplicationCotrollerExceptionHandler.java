package ru.kors.springstudents.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApplicationCotrollerExceptionHandler {

	
	 @ExceptionHandler
	 public ResponseEntity<String> handleException2(Exception e) {
		 
      return new ResponseEntity<>(e.getMessage() + " : [Application lavel]", HttpStatus.NOT_FOUND);
	 
      
	 }
	
}
