package com.rest.java.exception;

public class RestExcpetion extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public RestExcpetion(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public RestExcpetion() {
		super();
	}

	

}
