package io.github.dagurasu.corp.backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import io.github.dagurasu.corp.bo.CompaniesBO;
import io.github.dagurasu.corp.bo.EmployeeBO;
import io.github.dagurasu.corp.model.Company;
import io.github.dagurasu.corp.model.Employee;

@ManagedBean(name = "backingAddEmployee")
@ViewScoped
public class BackingAddEmployee {

	private Employee employee = new Employee();
	private EmployeeBO employeeBO = new EmployeeBO();
	private CompaniesBO companiesBO = new CompaniesBO();
	private List<Company> companiesList = new ArrayList<Company>();

	public List<Company> getCompaniesList() {
		return companiesList;
	}

	public void setCompaniesList(List<Company> companiesList) {
		this.companiesList = companiesList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String saveEmployee() {
		employeeBO.insertEmployee(employee);
		return "employees.xhtml?faces-redirect=true";
	}

	public void init() {
		this.companiesList = companiesBO.findlAllCompanies();

		Company company = new Company();
		company.setName("");
		companiesList.add(0, company);

	}

}
