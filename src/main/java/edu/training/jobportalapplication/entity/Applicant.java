package edu.training.jobportalapplication.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Applicant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private String applicantPassword;
	private long applicantPhNo;
	
	
@OneToMany(mappedBy = "applicant")
@JsonIgnore
private List<JobApplication> jobapplications;

@OneToOne
@JoinColumn
private Resume resume;

public long getApplicantId() {
	return applicantId;
}

public void setApplicantId(long applicantId) {
	this.applicantId = applicantId;
}

public String getApplicantName() {
	return applicantName;
}

public void setApplicantName(String applicantName) {
	this.applicantName = applicantName;
}

public String getApplicantEmail() {
	return applicantEmail;
}

public void setApplicantEmail(String applicantEmail) {
	this.applicantEmail = applicantEmail;
}

public String getApplicantPassword() {
	return applicantPassword;
}

public void setApplicantPassword(String applicantPassword) {
	this.applicantPassword = applicantPassword;
}

public long getApplicantPhNo() {
	return applicantPhNo;
}

public void setApplicantPhNo(long applicantPhNo) {
	this.applicantPhNo = applicantPhNo;
}

public List<JobApplication> getJobapplications() {
	return jobapplications;
}

public void setJobapplications(List<JobApplication> jobapplications) {
	this.jobapplications = jobapplications;
}

public Resume getResume() {
	return resume;
}

public void setResume(Resume resume) {
	this.resume = resume;
}


}
