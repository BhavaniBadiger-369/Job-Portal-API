package edu.training.jobportalapplication.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.ApplicantDao;
import edu.training.jobportalapplication.dao.ResumeDao;
import edu.training.jobportalapplication.dto.ResumeDto;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.exception.ApplicantNotFoundByIdException;
import edu.training.jobportalapplication.exception.ResumeNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class ResumeService {
	
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ApplicantDao applicantDao;
	
	public ResponseEntity<ResponseStructure<Resume>> saveResume(long applicantId ,ResumeDto resumeDto){
  Applicant applicant = applicantDao.getApplicantById(applicantId);

if(applicant!=null) {
 Resume existingResume =	applicant.getResume();
	Resume resume = this.modelMapper.map(resumeDto, Resume.class);

 if(existingResume!=null) {
	 resume.setResumeId(existingResume.getResumeId());
	 
 }
	resume.setApplicant(applicant);
	resume = resumeDao.saveResume(resume);
	
	applicant.setResume(resume);
	applicantDao.addApplicant(applicant);
	
	ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.CREATED.value());
	responseStructure.setMessage("Resume Added successfully");
	responseStructure.setData(resume);
	return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
	
}else {
	
 throw new ApplicantNotFoundByIdException("Failed to add Resume!");
}
	
		
	}

	public ResponseEntity<ResponseStructure<ResumeDto>> getResumeById(long resumeId){
	Resume resume = resumeDao.getResumeById(resumeId);
	
	if(resume!=null) {
		
	ResumeDto resumeDto = this.modelMapper.map(resume, ResumeDto.class);
		
		ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Resume Found!");
		responseStructure.setData(resumeDto);
		
		return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.FOUND);
	}else {
		
		throw new ResumeNotFoundByIdException("Failed to Find Resume with this Id!");
		
		
	}
	}
}
