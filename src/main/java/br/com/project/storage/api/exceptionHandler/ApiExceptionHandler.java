package br.com.project.storage.api.exceptionHandler;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.project.storage.exceptions.BusinessException;
import br.com.project.storage.exceptions.ExistingCustomerException;
import br.com.project.storage.exceptions.InactiveCustomerException;
import br.com.project.storage.exceptions.InvalidFormatException;

public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		var status = HttpStatus.BAD_GATEWAY;
		var issue = new Issue(ex.getMessage());
		issue.setStatus(status.value());
		issue.setTitle(ex.getMessage());
		issue.setDateTime(OffsetDateTime.now());

		return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);

	}
	
	@ExceptionHandler(InactiveCustomerException.class)
	public ResponseEntity<Object> handleInactiveCustomerException(InactiveCustomerException ex, WebRequest request){
		var status = HttpStatus.PRECONDITION_FAILED;
		var issue = new Issue(ex.getMessage());
		
		return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ExistingCustomerException.class)
	public ResponseEntity<Object> handleExistingCustomerException(ExistingCustomerException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var issue = new Issue(ex.getMessage());
		
		return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
	}
	
}
