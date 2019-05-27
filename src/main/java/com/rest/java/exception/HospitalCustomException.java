package com.rest.java.exception;

@SuppressWarnings("serial")
public class HospitalCustomException extends RuntimeException {

	public HospitalCustomException(int id) {
		super("Hospital I'd not Found= " + id);
	}

	public HospitalCustomException() {
		super("Hospitals unable to create");
	}

	public HospitalCustomException(String name) {
		super("hospital unable to create with the name is already exsits");
	}

}
