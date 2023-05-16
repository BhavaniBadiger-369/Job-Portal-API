package edu.training.jobportalapplication.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.EmployerDao;
import edu.training.jobportalapplication.dao.JobDao;
import edu.training.jobportalapplication.dto.JobDto;
import edu.training.jobportalapplication.entity.Employer;
import edu.training.jobportalapplication.entity.Job;
import edu.training.jobportalapplication.exception.EmployerNotFoundByIdExceeption;
import edu.training.jobportalapplication.exception.JobNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class JobService {
	
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployerDao employerDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public ResponseEntity<ResponseStructure<Job>> addJob(JobDto jobDto, long employerId){
		
	Employer employer = employerDao.getEmployerById(employerId);
	if (employer!=null) {
		
		Job job= this.modelMapper.map(jobDto, Job.class);
		
		job.setJobCreateDateTime(LocalDateTime.now());
		job.setEmployer(employer);
		
		jobDao.addJob(job);
		
		employer.getJobs().add(job);
		employerDao.addEmployer(employer);
		
		ResponseStructure<Job> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Job adeed Successfully");
		responseStructure.setData(job);
		
		return new ResponseEntity<ResponseStructure<Job>>(responseStructure, HttpStatus.ACCEPTED.CREATED);
		
	}else {
		throw new EmployerNotFoundByIdExceeption("Failed to add Job!");
	}
}
	
	public ResponseEntity<ResponseStructure<JobDto>> getJobById(long jobId){
		Job job = jobDao.getJobById(jobId);
		
		if(job!=null) {
		
		JobDto jobDto = this.modelMapper.map(job, JobDto.class);
			
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Job Found!");
		responseStructure.setData(jobDto);
		
	       return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.FOUND);
		
		
		}else {
			
			throw new JobNotFoundByIdException("Failed to find Job with this Id!");
		}
		}

}
