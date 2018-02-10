package com.abargougui.springboottraining.exceptionhandler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(NoSuchElementException.class)
	public String handleEementNotFound(NoSuchElementException e) {
		return "Could not find element! " + e.getMessage();
	}
}
