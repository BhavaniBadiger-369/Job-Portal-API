package edu.training.jobportalapplication.entity;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class JobApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobApplicationId;
	private LocalDateTime jobApplicationDateTime;

	@ManyToOne
	@JoinColumn
	private Job job;
	
	@ManyToOne
	@JoinColumn
	private Applicant applicant;
	

	public long getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(long jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}

	public LocalDateTime getJobApplicationDateTime() {
		return jobApplicationDateTime;
	}

	public void setJobApplicationDateTime(LocalDateTime jobApplicationDateTime) {
		this.jobApplicationDateTime = jobApplicationDateTime;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	
}
