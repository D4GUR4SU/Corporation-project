package io.github.dagurasu.corp.exception;

import java.util.List;

public class EmployeeValidatorException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmployeeValidatorException(List<String> errors) {
		super(errors.toString());

	}
}
