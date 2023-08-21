package edu.training.jobportalapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.training.jobportalapplication.entity.JobApplication;
import edu.training.jobportalapplication.service.JobApplicationService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/application")
public class JobApplicationController {

	@Autowired
	JobApplicationService jobApplicationService;

	@PostMapping
	public ResponseEntity<ResponseStructure<JobApplication>> createJobApplication(@RequestParam long applicantId,
			@RequestParam long jobId) {

		return jobApplicationService.createJobApplication(applicantId, jobId);
	}

	@GetMapping("/applicant")

	public ResponseEntity<ResponseStructure<List<JobApplication>>> getJobApplicationByApplicantId(
			@RequestParam long applicantId) {
		return jobApplicationService.getApplicationByApplicantId(applicantId);
	}

	@GetMapping("/job")

	public ResponseEntity<ResponseStructure<List<JobApplication>>> getJobApplicationByJobId(@RequestParam long jobId) {

		return jobApplicationService.getApplicationsByJobId(jobId);
	}

}
