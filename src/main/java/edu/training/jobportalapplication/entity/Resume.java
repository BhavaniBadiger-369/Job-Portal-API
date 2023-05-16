package edu.training.jobportalapplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resumeId;
	private String summary;
	private String qualification;
	private String university;
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
    private String certification;
    
    @OneToOne(mappedBy = "resume")
    @JsonIgnore
    private Applicant applicant;
    
   @ManyToMany(cascade = CascadeType.ALL)
    private List<Skill> skills;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;

	public long getResumeId() {
		return resumeId;
	}

	public void setResumeId(long resumeId) {
		this.resumeId = resumeId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

  public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getSocialProfile1() {
		return socialProfile1;
	}

	public void setSocialProfile1(String socialProfile1) {
		this.socialProfile1 = socialProfile1;
	}

	public String getSocialProfile2() {
		return socialProfile2;
	}

	public void setSocialProfile2(String socialProfile2) {
		this.socialProfile2 = socialProfile2;
	}

	public String getSocialProfile3() {
		return socialProfile3;
	}

	public void setSocialProfile3(String socialProfile3) {
		this.socialProfile3 = socialProfile3;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	

}
