package edu.training.jobportalapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.service.SkillService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>>saveSkills(long applicantId, @RequestParam String[] skills){
		return skillService.saveSkill(applicantId, skills);
		
	}
}
