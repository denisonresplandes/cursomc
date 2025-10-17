package com.denisonresplandes.cursomc.resources.exceptions.handler;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.denisonresplandes.cursomc.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardErrorMessage> handleResourceNotFound(ResourceNotFoundException e,
			HttpServletRequest request) {
		StandardErrorMessage standardError = createStandardErrorMessage(e.getMessage(), 
				request, HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
	private StandardErrorMessage createStandardErrorMessage(String message, 
			HttpServletRequest request, HttpStatus status) {
		StandardErrorMessage standardError = new StandardErrorMessage(
				ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS), 
				status.getReasonPhrase(), 
				status.value(), 
				message, 
				request.getRequestURI());
		return standardError;
	}
	
	private record StandardErrorMessage (
		ZonedDateTime timestamp,
		String title,
		int status,
		String detail,
		String path
	) {}
}
