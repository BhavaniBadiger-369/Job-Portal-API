package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class ApplicantNotFoundByIdException extends RuntimeException {

	private String message;

	public ApplicantNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
