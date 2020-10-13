package io.github.dagurasu.corp.model;

import java.util.Date;

public class TokenUser {

	private String token;
	private Date expirationDate;

	public TokenUser() {
		super();
	}

	public TokenUser(String token, Date expirationDate) {
		super();
		this.token = token;
		this.expirationDate = expirationDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "TokenUser [token=" + token + ", expirationDate=" + expirationDate + "]";
	}

}
