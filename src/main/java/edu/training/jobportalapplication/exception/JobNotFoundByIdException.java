package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class JobNotFoundByIdException extends RuntimeException {
	
	private String message;

	public JobNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
