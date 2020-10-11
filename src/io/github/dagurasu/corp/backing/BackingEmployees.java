package io.github.dagurasu.corp.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import io.github.dagurasu.corp.bo.EmployeeBO;
import io.github.dagurasu.corp.model.Employee;

@ManagedBean(name = "backingEmployees")
@SessionScoped
public class BackingEmployees implements Serializable {

	private static final long serialVersionUID = 1L;

	private EmployeeBO employeeBO = new EmployeeBO();

	@ManagedProperty(value = "#{backingEditEmployee}")
	private BackingEditEmployee backingEditEmployee;

	public BackingEditEmployee getBackingEditEmployee() {
		return backingEditEmployee;
	}

	public void setBackingEditEmployee(BackingEditEmployee backingEditEmployee) {
		this.backingEditEmployee = backingEditEmployee;
	}

	public void delete(Employee employee) {
		employeeBO.deleteEmployee(employee);
	}

	public String edit(Employee employee) throws IOException {
		backingEditEmployee.setEmployee(employee);
		return "update-employee.xhtml?faces-redirect=true";
	}

	public List<Employee> findAllEmployees() {
		return employeeBO.findAllEmployees();
	}
}
