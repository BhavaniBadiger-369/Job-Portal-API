package edu.training.jobportalapplication.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.ApplicantDao;
import edu.training.jobportalapplication.dao.ProjectDao;
import edu.training.jobportalapplication.dao.ResumeDao;
import edu.training.jobportalapplication.dao.SkillDao;
import edu.training.jobportalapplication.dto.ResumeDto;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.exception.ApplicantNotFoundByIdException;
import edu.training.jobportalapplication.exception.ResumeNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ApplicantDao applicantDao;

	public ResponseEntity<ResponseStructure<Resume>> saveResume(long applicantId, ResumeDto resumeDto) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {
			Resume existingResume = applicant.getResume();
			Resume resume = this.modelMapper.map(resumeDto, Resume.class);

			if (existingResume != null) {
				resume.setResumeId(existingResume.getResumeId());
				resume.setProjects(existingResume.getProjects());
				resume.setSkills(existingResume.getSkills());

			}
			resume.setApplicant(applicant);
			resume = resumeDao.saveResume(resume);

			applicant.setResume(resume);
			applicantDao.addApplicant(applicant);

			ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Resume Added/updated successfully");
			responseStructure.setData(resume);
			return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);

		} else {

			throw new ApplicantNotFoundByIdException("Failed to add Resume!");
		}

	}

	public ResponseEntity<ResponseStructure<Resume>> getResumeById(long resumeId) {

		Optional<Resume> optional = resumeDao.getResumeById(resumeId);

		if (optional.isPresent()) {

			ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Resume Found!");
			responseStructure.setData(optional.get());

			return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.FOUND);
		} else {

			throw new ResumeNotFoundByIdException("Failed to Find Resume with this Id!");

		}
	}

	public ResponseEntity<ResponseStructure<Resume>> deleteResume(long resumeId, long applicantId) {

		Optional<Resume> optional = resumeDao.getResumeById(resumeId);

		if (optional.isPresent()) {

			Applicant applicant = applicantDao.getApplicantById(applicantId);

			if (applicant != null) {

				Resume resume = applicant.getResume();

				resume.getProjects().remove(optional.get());
				resume.getSkills().remove(optional.get());
				applicantDao.addApplicant(applicant);

				resumeDao.deleteResume(optional.get());

				ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Resume Deleted!");
				responseStructure.setData(optional.get());

				return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.OK);

			} else {

				throw new ApplicantNotFoundByIdException("Failed to delete Resume!");
			}

		} else {

			throw new ResumeNotFoundByIdException("Failed to  delete Resume!");

		}

	}

}