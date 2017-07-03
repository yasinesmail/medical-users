package com.medley.users.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

@ControllerAdvice(basePackages = {"com.medley.users"})
public class GlobalControllerExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody ErrorInfo
    handleNotFound(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURI(), ex);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody ErrorInfo
    handleIllegalArgumentException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURI(), ex);
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody ErrorInfo
    defaultErrorHandler(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(req.getRequestURI(), ex);
	}

}
