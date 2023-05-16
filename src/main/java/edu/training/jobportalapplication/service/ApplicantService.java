package edu.training.jobportalapplication.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.ApplicantDao;
import edu.training.jobportalapplication.dto.ApplicantDto;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.exception.ApplicantNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<ApplicantDto>> saveApplicant(Applicant applicant){
		
     applicant =	applicantDao.addApplicant(applicant);
 	ApplicantDto applicantDto =	this.modelMapper.map(applicant, ApplicantDto.class);
 	
 	ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
 	responseStructure.setStatusCode(HttpStatus.CREATED.value());
 	responseStructure.setMessage("Applicant added successfully");
 	responseStructure.setData(applicantDto);
 	
 return new  ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);

	}
	
	public ResponseEntity<ResponseStructure<ApplicantDto>>getApplicantById(long applicantId){
	Applicant applicant = applicantDao.getApplicantById(applicantId);
		
	if(applicant!=null) {
	ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
	
	ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.FOUND.value());
	responseStructure.setMessage("Applicant Found!");
	responseStructure.setData(applicantDto);
	return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND);
	
	}else {
		throw new ApplicantNotFoundByIdException("Failed to find Applicant with this Id!");
	}
	}
}

