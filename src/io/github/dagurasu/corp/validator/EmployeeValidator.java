package io.github.dagurasu.corp.validator;

import java.util.ArrayList;
import java.util.List;

import io.github.dagurasu.corp.exception.EmployeeValidatorException;
import io.github.dagurasu.corp.model.Employee;

public class EmployeeValidator {

	private Employee employee;

	public EmployeeValidator(Employee employee) {
		this.employee = employee;
	}

	public void validate() throws EmployeeValidatorException {
		List<String> errors = new ArrayList<>();

		long id = employee.getId();
		if (id <= 0) {
			errors.add("Invalid id");
		}

		String name = employee.getFirstName();
		if (isEmpty(name) || name.length() < 3) {
			errors.add("Invalis name");
		}

		if (!errors.isEmpty()) {
//			throw new RuntimeException("argg"); 

			throw new EmployeeValidatorException(errors);
		}
	}

	private boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

}
