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

import edu.training.jobportalapplication.dto.JobDto;
import edu.training.jobportalapplication.dto.JobResponse;
import edu.training.jobportalapplication.entity.Job;
import edu.training.jobportalapplication.service.JobService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/job")
public class JobController {

@Autowired
private JobService jobService;

@PostMapping
public ResponseEntity<ResponseStructure<JobResponse>> addJob(@RequestBody JobDto jobDto, @RequestParam long employerId, @RequestParam String []skills){
	return jobService.addJob(jobDto, employerId, skills);
	
}

@GetMapping
public ResponseEntity<ResponseStructure<JobResponse>>getJobById( @RequestParam long jobId){
	return jobService.getJobById(jobId);
}

@GetMapping("/skill")
public ResponseEntity<ResponseStructure<List<JobResponse>>>getJobBySkills(@RequestParam long skillId){
	return jobService.getJobsBySkill(skillId);
}

@PutMapping
public ResponseEntity<ResponseStructure<JobResponse>>updateJobById(@RequestBody JobDto jobDto,@RequestParam long jobId){
    return jobService.updateJobById(jobDto, jobId);
}
	
@DeleteMapping
public ResponseEntity<ResponseStructure<JobResponse>>deleteJobById(@RequestParam long jobId){
	return jobService.deleteJobById(jobId);
}
}
