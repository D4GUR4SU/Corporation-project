package io.github.dagurasu.corp.filter;

import io.github.dagurasu.corp.model.Company;

public class Filter {

	private Company company;
	private String name;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
