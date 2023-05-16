package edu.training.jobportalapplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.repository.ApplicantRepo;

@Repository
public class ApplicantDao {

	@Autowired
	private ApplicantRepo applicantRepo;
	
	public Applicant addApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
		
	}

	public Applicant getApplicantById(long applicantId) {
	Optional<Applicant> optional = applicantRepo.findById(applicantId);
	
		if(optional.isEmpty()) {
			return null;

		}else {
			
			return	optional.get();
	}
	

		
	}
}
