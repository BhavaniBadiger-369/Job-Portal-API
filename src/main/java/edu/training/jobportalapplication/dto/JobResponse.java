package edu.training.jobportalapplication.dto;

import java.time.LocalDateTime;
import java.util.List;

import edu.training.jobportalapplication.entity.Employer;

public class JobResponse {
	
	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String company;
	private double  salary;
	private LocalDateTime jobCreateDateTime;
	
	private List<String> skills;
	
	private Employer employer;

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDateTime getJobCreateDateTime() {
		return jobCreateDateTime;
	}

	public void setJobCreateDateTime(LocalDateTime jobCreateDateTime) {
		this.jobCreateDateTime = jobCreateDateTime;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
	

}
