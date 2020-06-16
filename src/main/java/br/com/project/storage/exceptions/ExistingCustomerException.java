package br.com.project.storage.exceptions;

public class ExistingCustomerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistingCustomerException() {
		super("CPF já cadastrado no sistema. Por favor, valide os dados e tente novamente.");
	}
}
