package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class AlreadyAppliedToJobException extends RuntimeException {
 
	private String message;

	public AlreadyAppliedToJobException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
