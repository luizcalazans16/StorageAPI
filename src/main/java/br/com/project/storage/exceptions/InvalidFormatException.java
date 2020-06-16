package br.com.project.storage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidFormatException(String message) {
		super(message);
	}
	
}
