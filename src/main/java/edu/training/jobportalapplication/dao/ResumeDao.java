package edu.training.jobportalapplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.repository.ResumeRepo;

@Repository
public class ResumeDao {

	@Autowired
	private ResumeRepo resumeRepo;
	
	public Resume saveResume(Resume resume) {
		return resumeRepo.save(resume);
	}
	
	public Resume getResumeById( long resumeId) {
	Optional<Resume> optional = resumeRepo.findById(resumeId);
	
	if(optional.isEmpty()) {
		return null;
	}else {
		
	return	optional.get();
	}
		
	}
}
