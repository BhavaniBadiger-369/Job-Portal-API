package edu.training.jobportalapplication.exception;

@SuppressWarnings("serial")
public class JobNotFoundBySkillException extends RuntimeException {
	
	private String message;

	public JobNotFoundBySkillException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
