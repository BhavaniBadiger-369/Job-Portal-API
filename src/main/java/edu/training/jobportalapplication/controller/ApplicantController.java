package edu.training.jobportalapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jobportalapplication.dto.ApplicantDto;
import edu.training.jobportalapplication.dto.ApplicantResponse;
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
	public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicantById(@RequestParam long  applicantId){
		return applicantService.getApplicantById(applicantId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>>updateAppicantById(@RequestParam long appicantId, @RequestBody Applicant applicant){
		return applicantService.updateApplicantById(appicantId, applicant);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>>deleteApplicantById(@RequestParam long applicantId){
		return applicantService.deleteApplicantById(applicantId);
	}
	
	@GetMapping("/skill")
	public ResponseEntity<ResponseStructure<List<ApplicantResponse>>> getApplicantBySkill(@RequestParam String skill){
		return applicantService.getApplicantBySkill(skill);
	}

}
