package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class ProjectNotFoundByIdException extends RuntimeException {

	private String message;

	public ProjectNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
