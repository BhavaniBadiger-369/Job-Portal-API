package edu.training.jobportalapplication.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employerId;
	private String employerName;
	private String employerEmail;
	private String employerPassword;
	
	@OneToMany(mappedBy = "employer")
	@JsonIgnore
	private List<Job> jobs;

	public long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerEmail() {
		return employerEmail;
	}

	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}

	public String getEmployerPassword() {
		return employerPassword;
	}

	public void setEmployerPassword(String employerPassword) {
		this.employerPassword = employerPassword;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}



	
}
