
package edu.training.jobportalapplication.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.EmployerDao;
import edu.training.jobportalapplication.dao.JobDao;
import edu.training.jobportalapplication.dto.EmployerDto;
import edu.training.jobportalapplication.entity.Employer;
import edu.training.jobportalapplication.entity.Job;
import edu.training.jobportalapplication.exception.EmployerNotFoundByIdExceeption;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class EmployerService {

	@Autowired
	private EmployerDao employerDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JobDao jobDao;
	
	public ResponseEntity<ResponseStructure<Employer>> addEmployer(Employer employer){
		Employer employer2 =	employerDao.addEmployer(employer);
 
 ResponseStructure<Employer> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.CREATED.value());
	responseStructure.setMessage("Employer adeed Successfully!");
	responseStructure.setData(employer2);
	
	return new ResponseEntity<ResponseStructure<Employer>>(responseStructure, HttpStatus.CREATED);
	

	}
	
	public ResponseEntity<ResponseStructure<EmployerDto>>getEmployerById(long employerId){
	Employer employer = employerDao.getEmployerById(employerId);
	
	if(employer!=null) {
	EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
		
	ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.FOUND.value());
	responseStructure.setMessage("Employer Found!");
	responseStructure.setData(employerDto);
	
	return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.FOUND);
		
	}else {
		throw new EmployerNotFoundByIdExceeption("Failed to find Employer with this Id");
	}
	}
	
	public ResponseEntity<ResponseStructure<EmployerDto>>updateEmployerById(long employerId, Employer employer ){
	Employer exEmployer = employerDao.getEmployerById(employerId);
	
	if(exEmployer!=null) {
		employer.setEmployerId(exEmployer.getEmployerId());
		employer.setJobs(exEmployer.getJobs());
	    employer =	employerDao.addEmployer(employer);
	  EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
	  
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Employer Updated Successfully!!");
		responseStructure.setData(employerDto);
		
		return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
					
	}else {
		throw new EmployerNotFoundByIdExceeption("Failed to Find Employer");
	}
	}
		
	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployerById(long employerId){
	Employer employer = employerDao.getEmployerById(employerId);
	
	if(employer!=null) {
		for(Job job:employer.getJobs()) {
			job.setEmployer(null);
			jobDao.addJob(job);
		}
		
		employerDao.deleteEmployer(employer);
		
		  EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
		  
			ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Employer Deleted Successfully!!");
			responseStructure.setData(employerDto);
			
			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
		
		
	}else {
		throw new EmployerNotFoundByIdExceeption("Failed to delete Employer!!");
	}
	}
	}

