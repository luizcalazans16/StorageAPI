package br.com.project.storage.exceptions;

public class InactiveCustomerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InactiveCustomerException() {
		super("O cliente encontra-se inativo no sistema.");
	}
}
