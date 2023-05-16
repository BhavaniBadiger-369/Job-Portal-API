package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class ResumeNotFoundByIdException extends RuntimeException {

	private String message;

	public ResumeNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
