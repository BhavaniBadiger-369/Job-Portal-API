package edu.training.jobportalapplication.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.ApplicantDao;
import edu.training.jobportalapplication.dao.JobApplicationDao;
import edu.training.jobportalapplication.dao.JobDao;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.entity.Job;
import edu.training.jobportalapplication.entity.JobApplication;
import edu.training.jobportalapplication.exception.AlreadyAppliedToJobException;
import edu.training.jobportalapplication.exception.ApplicantNotFoundByIdException;
import edu.training.jobportalapplication.exception.JobNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class JobApplicationService {

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private JobDao jobDao;

	public ResponseEntity<ResponseStructure<JobApplication>> createJobApplication(long applicantId, long jobId) {

		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {

			Job job = jobDao.getJobById(jobId);

			if (job != null) {

				// check if applicant has already applied to thejob or not
				List<JobApplication> applications = applicant.getJobapplications();

				for (JobApplication a : applications) {
					if (a.getJob().equals(job)) {

						throw new AlreadyAppliedToJobException(" Failed to add JobApplication!");
					}

				}

				JobApplication application = new JobApplication();
				application.setJobApplicationDateTime(LocalDateTime.now());
				application.setJob(job);
				application.setApplicant(applicant);

				// saving the job application object

				application = jobApplicationDao.createJobApplication(application);

				// setting and updating job application for job

				job.getJobApplications().add(application);
				jobDao.addJob(job);

				// setting and updating job application for applicant

				applicant.getJobapplications().add(application);
				applicantDao.addApplicant(applicant);

				ResponseStructure<JobApplication> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Job Application added successfully!");
				responseStructure.setData(application);
				return new ResponseEntity<ResponseStructure<JobApplication>>(responseStructure, HttpStatus.CREATED);

			} else {

				throw new JobNotFoundByIdException("Failed to create job Application!!");
			}
		} else {
			throw new ApplicantNotFoundByIdException("Failed to create job Application!!");
		}

	}

	public ResponseEntity<ResponseStructure<List<JobApplication>>> getApplicationByApplicantId(long applicantId) {

		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {
			ResponseStructure<List<JobApplication>> responseStructre = new ResponseStructure<>();
			responseStructre.setStatusCode(HttpStatus.FOUND.value());
			responseStructre.setMessage("Job application found by applicantId");
			responseStructre.setData(applicant.getJobapplications());
			return new ResponseEntity<ResponseStructure<List<JobApplication>>>(responseStructre, HttpStatus.FOUND);

		} else {

			throw new ApplicantNotFoundByIdException("Failed to find JobApplication!");
		}

	}

	public ResponseEntity<ResponseStructure<List<JobApplication>>> getApplicationsByJobId(long jobId) {

		Job job = jobDao.getJobById(jobId);
		if (job != null) {

			ResponseStructure<List<JobApplication>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job application Found byJobId");
			responseStructure.setData(job.getJobApplications());

			return new ResponseEntity<ResponseStructure<List<JobApplication>>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new JobNotFoundByIdException("Failed to find JobApplication");
		}

	}

}
