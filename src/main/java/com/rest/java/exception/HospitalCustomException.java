package com.rest.java.exception;

@SuppressWarnings("serial")
public class HospitalCustomException extends RuntimeException {

	public HospitalCustomException(int id) {
		super("Hospital I'd not Found= " + id);
	}

	public HospitalCustomException() {
		super("Hospitals can't be added");
	}

}


