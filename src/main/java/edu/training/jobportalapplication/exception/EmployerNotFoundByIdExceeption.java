package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class EmployerNotFoundByIdExceeption extends RuntimeException {

	private String message;

	public EmployerNotFoundByIdExceeption(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
}
