package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class SkillNotFoundByIdException extends RuntimeException {

	private String message;

	public SkillNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

		
}
