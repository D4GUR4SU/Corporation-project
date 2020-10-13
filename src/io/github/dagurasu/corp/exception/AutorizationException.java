package io.github.dagurasu.corp.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "AutorizationFault")
public class AutorizationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutorizationException(String msg) {
		super(msg);
	}

	public String getFaultInfo() {
		return "invalid Token!";
	}

}
