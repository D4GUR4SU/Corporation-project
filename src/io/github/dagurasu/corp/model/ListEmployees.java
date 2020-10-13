package io.github.dagurasu.corp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListEmployees {

	@XmlElement(name = "employee")
	private List<Employee> employees;

	public ListEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	 ListEmployees() {
	}

	public List<Employee> getEmployees() {
		return employees;
	}

}
