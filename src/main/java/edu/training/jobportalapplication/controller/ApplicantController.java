package edu.training.jobportalapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jobportalapplication.dto.ApplicantDto;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.service.ApplicantService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {
	@Autowired
	private ApplicantService applicantService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> saveApplicant(@RequestBody Applicant applicant){
		return applicantService.saveApplicant(applicant);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> getapplicantById(@RequestParam long  applicantId){
		return applicantService.getApplicantById(applicantId);
	}

}
