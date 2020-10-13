package io.github.dagurasu.corp.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import io.github.dagurasu.corp.backing.BackingEmployees;
import io.github.dagurasu.corp.dao.TokenDao;
import io.github.dagurasu.corp.exception.AutorizationException;
import io.github.dagurasu.corp.exception.EmployeeValidatorException;
import io.github.dagurasu.corp.filter.Filters;
import io.github.dagurasu.corp.model.Employee;
import io.github.dagurasu.corp.model.ListEmployees;
import io.github.dagurasu.corp.model.TokenUser;
import io.github.dagurasu.corp.validator.EmployeeValidator;

@WebService(name = "corporationSOAPWS")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class CorporationSOAPWS {

	BackingEmployees backingEmployees = new BackingEmployees();

	@WebMethod(operationName = "AllEmployees")
	@WebResult(name = "employees")
	public ListEmployees getEmployees(@WebParam(name = "filter") Filters filters) {

		System.out.println("Calling getEmployees()...");

		List<Employee> list = backingEmployees.findAllEmployees();
		return new ListEmployees(list);

	}

	@WebMethod(action = "RegisterEmployee", operationName = "RegisterEmployee")
	@WebResult(name = "employee")
	public Employee registerEmployee(
			@WebParam(name = "token", header = true) TokenUser token,
			@WebParam(name = "employee") Employee employee) 
					throws AutorizationException, EmployeeValidatorException {

		System.out.println("Resgistering employee..." + employee + ", Token: " + token);

		boolean valid = new TokenDao().isValid(token);

		if (!valid) {
			throw new AutorizationException("Authorization failed!");
		}

		new EmployeeValidator(employee).validate();

		this.backingEmployees.register(employee);
		return employee;
	}

}
