package io.github.dagurasu.corp.backing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import io.github.dagurasu.corp.bo.CompaniesBO;
import io.github.dagurasu.corp.bo.EmployeeBO;
import io.github.dagurasu.corp.model.Company;
import io.github.dagurasu.corp.model.Employee;

@ManagedBean(name = "backingEditEmployee")
@SessionScoped
public class BackingEditEmployee {

	private Employee employee = new Employee();
	private EmployeeBO employeeBO = new EmployeeBO();
	private CompaniesBO companiesBO = new CompaniesBO();
	private List<Company> companiesList = new ArrayList<Company>();

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Company> getCompaniesList() {
		return companiesList;
	}

	public void init() throws IOException {
		this.companiesList = companiesBO.findlAllCompanies();

		Company company = new Company();
		company.setName("");
		companiesList.add(0, company);
	}

	public String updateEmployee() {
		employeeBO.updateEmployee(employee);
		return "employees.xhtml?faces-redirect=true";
	}

}
