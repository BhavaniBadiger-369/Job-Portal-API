package edu.training.jobportalapplication.controller;

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

import edu.training.jobportalapplication.dto.ResumeDto;
import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.service.ResumeService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/resume")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> saveResume( @RequestParam long applicantId, @RequestBody ResumeDto resumeDto){
		return resumeService.saveResume(applicantId, resumeDto);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Resume>> getResumeById( @RequestParam  long resumeId){
	return	resumeService.getResumeById(resumeId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Resume>> updateResume(@RequestParam long applicantId, @RequestBody ResumeDto resumedto){
	return	resumeService.saveResume(applicantId, resumedto);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Resume>>deleteResume(@RequestParam long resumeId, @RequestParam long applicantId){
	return	resumeService.deleteResume(resumeId, applicantId);
	}
}
