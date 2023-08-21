package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class SkillNotFoundByNameException extends RuntimeException {

	private String message;

	public SkillNotFoundByNameException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
