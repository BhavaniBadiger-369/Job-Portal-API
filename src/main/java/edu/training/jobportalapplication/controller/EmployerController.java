package edu.training.jobportalapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jobportalapplication.dto.EmployerDto;
import edu.training.jobportalapplication.entity.Employer;
import edu.training.jobportalapplication.service.EmployerService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/employer")
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Employer>> addEmployer(@RequestBody Employer employer){
		return employerService.addEmployer(employer);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<EmployerDto>>getEmployerById(@RequestParam long employerId){
		return employerService.getEmployerById(employerId);
	}
}
