package io.github.dagurasu.corp.bo;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import io.github.dagurasu.corp.model.Company;

public class CustomSystemEventListener implements SystemEventListener {

	@Override
	public boolean isListenerForSource(Object value) {
		return (value instanceof Application);
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		if (event instanceof PostConstructApplicationEvent) {
			System.out.println("Application Started. PostConstructApplicationEvent ocurred!");

			EmployeeBO employeeBo = new EmployeeBO();
			employeeBo.createEmployeeTable();

			CompaniesBO companiesBO = new CompaniesBO();
			String result = companiesBO.createCompaniesTable();

			if (result.equals("Created")) {
				
				Company company = new Company();
				company.setName("Dagurasu Corp.");
				companiesBO.insertCompany(company);
				
				company.setName("Evil Corp.");
				companiesBO.insertCompany(company);
				
				company.setName("F.Society");
				companiesBO.insertCompany(company);

				company.setName("Genesys");
				companiesBO.insertCompany(company);
				
				company.setName("Scorpion");
				companiesBO.insertCompany(company);
				
				company.setName("S.T.A.R");
				companiesBO.insertCompany(company);
				
				company.setName("Raz3R");
				companiesBO.insertCompany(company);
			}

		}
		if (event instanceof PreDestroyApplicationEvent) {
			System.out.println("PreDestroyApplicationEvent ocurred. Application is stopping.");
		}
	}

}
