package edu.training.jobportalapplication.dto;

public class ApplicantDto {

	 private long applicantId;
		private String applicantName;
		private String applicantEmail;
       private long applicantPhNo;
       
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
	public long getApplicantPhNo() {
		return applicantPhNo;
	}
	public void setApplicantPhNo(long applicantPhNo) {
		this.applicantPhNo = applicantPhNo;
	}
       
       
}
