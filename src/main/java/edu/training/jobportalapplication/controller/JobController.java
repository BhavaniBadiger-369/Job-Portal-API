package edu.training.jobportalapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jobportalapplication.dto.JobDto;
import edu.training.jobportalapplication.entity.Job;
import edu.training.jobportalapplication.service.JobService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/job")
public class JobController {

@Autowired
private JobService jobService;

@PostMapping
public ResponseEntity<ResponseStructure<Job>> addJob(@RequestBody JobDto jobDto , @RequestParam long employerId){
	return jobService.addJob(jobDto, employerId);
	
}

@GetMapping
public ResponseEntity<ResponseStructure<JobDto>>getJobById( @RequestParam long jobId){
	return jobService.getJobById(jobId);
}
	
}
