package io.github.dagurasu.corp.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import io.github.dagurasu.corp.model.User;

@ManagedBean
public class LoginMB {

	private User user = new User();

	public String doLogin() {
		if ("dagurasu".equals(user.getLogin()) && "admin".equals(user.getPassword())) {

			return "employees.xhtml?faces-redirect=true";
		} else {

			FacesContext.getCurrentInstance().addMessage("erro",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password!", null));

			return "erro";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
