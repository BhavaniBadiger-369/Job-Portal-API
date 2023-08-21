package edu.training.jobportalapplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.repository.ResumeRepo;
import edu.training.jobportalapplication.util.ResponseStructure;

@Repository
public class ResumeDao {

	@Autowired
	private ResumeRepo resumeRepo;
	
	public Resume saveResume(Resume resume) {
		return resumeRepo.save(resume);
	}
	
	public  Optional<Resume> getResumeById( long resumeId) {
	return resumeRepo.findById(resumeId);
		
	}
	
//	public Resume updateResume(long resumeId, Resume resume) {
//	Optional<Resume> optional = resumeRepo.findById(resumeId);
//	if(optional.isEmpty()) {
//		return null;
//	}else {
//		resume.setResumeId(resumeId);
//	return	resumeRepo.save(resume);
//	}
//	}
	
	
	public void  deleteResume(Resume resume) {
		resumeRepo.delete(resume);
	}
}
