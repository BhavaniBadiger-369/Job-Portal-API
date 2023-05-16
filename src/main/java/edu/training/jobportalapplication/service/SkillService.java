package edu.training.jobportalapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.ApplicantDao;
import edu.training.jobportalapplication.dao.ResumeDao;
import edu.training.jobportalapplication.dao.SkillDao;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.entity.Skill;
import edu.training.jobportalapplication.exception.ApplicantNotFoundByIdException;
import edu.training.jobportalapplication.exception.ResumeNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class SkillService {
	
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private ApplicantDao applicantDao;
	
	@Autowired
	private ResumeDao resumeDao;
	
	
	public ResponseEntity<ResponseStructure<Resume>> saveSkill(long applicantId, String[] skills){
	Applicant applicant =	applicantDao.getApplicantById(applicantId);
	if(applicant!=null) {
      Resume resume = applicant.getResume();
if(resume!=null) {
	
	for(String skill : skills) {
	 Skill existingSkill =	skillDao.getSkillByName(skill);
	 if(existingSkill!=null) {
		 resume.getSkills().add(existingSkill);
	 }else {
		 Skill newSkill = new Skill();
		 newSkill.setSkillName(skill);
		 resume.getSkills().add(newSkill);
	 }
	}
	

	 resume =resumeDao.saveResume(resume);
	
	ResponseStructure<Resume>  responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Resume Added successfully");
		responseStructure.setData(resume);
		return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
}else {
	
}
	throw new ResumeNotFoundByIdException("Failed to add skills! ");
	
}else {
	
	throw new ApplicantNotFoundByIdException("Failed to add Resume!");
}
	
}
	
}

	

