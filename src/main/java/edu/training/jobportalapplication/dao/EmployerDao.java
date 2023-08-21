
package edu.training.jobportalapplication.dao;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.entity.Employer;
import edu.training.jobportalapplication.repository.EmployerRepo;


@Repository
public class EmployerDao {

	@Autowired
	private EmployerRepo employerRepo;
	
	public Employer addEmployer(Employer employer) {
		return employerRepo.save(employer);
		
	}

	public Employer getEmployerById(long employerId) {
		
	Optional<Employer> optional = employerRepo.findById(employerId);
	
	if(optional.isEmpty()) {
		return null; 
	}else {
		return optional.get();
	}
	}
	
	public Employer updateEmployer(long employerId, Employer employer) {
	Optional<Employer> optional = employerRepo.findById(employerId);
	
	if(optional.isEmpty()) {
		return null;
	}else {
		employer.setEmployerId(employerId);
	return	employerRepo.save(employer);
	}
		
	}
	
	public void deleteEmployer(Employer employer) {
		employerRepo.delete(employer);
	}
	
	
	
	
}
