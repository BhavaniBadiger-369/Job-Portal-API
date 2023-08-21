package edu.training.jobportalapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.ApplicantDao;
import edu.training.jobportalapplication.dao.JobApplicationDao;
import edu.training.jobportalapplication.dao.ProjectDao;
import edu.training.jobportalapplication.dao.ResumeDao;
import edu.training.jobportalapplication.dto.ApplicantDto;
import edu.training.jobportalapplication.dto.ApplicantResponse;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.entity.JobApplication;
import edu.training.jobportalapplication.entity.Project;
import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.entity.Skill;
import edu.training.jobportalapplication.exception.ApplicantNotFoundByIdException;
import edu.training.jobportalapplication.exception.SkillNotFoundByNameException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private ProjectDao projectDao;

	public ResponseEntity<ResponseStructure<ApplicantDto>> saveApplicant(Applicant applicant) {

		applicant = applicantDao.addApplicant(applicant);
		ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);

		ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Applicant added successfully");
		responseStructure.setData(applicantDto);

		return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicantById(long applicantId) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {
			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);

			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant Found!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND);

		} else {
			throw new ApplicantNotFoundByIdException("Failed to find Applicant with this Id!");
		}
	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicantById(long applicantId, Applicant applicant) {
		Applicant exApplicant = applicantDao.getApplicantById(applicantId);
		if (exApplicant != null) {
			applicant.setApplicantId(exApplicant.getApplicantId());
			applicant.setJobapplications(exApplicant.getJobapplications());
			applicant.setResume(exApplicant.getResume());
			applicantDao.addApplicant(applicant);

			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant Updated Successfully!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);

		} else {

			throw new ApplicantNotFoundByIdException("Failed to update applicant!!");
		}
	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicantById(long applicantId) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);
		if (applicant != null) {
			for (JobApplication jobApplication : applicant.getJobapplications()) {
				jobApplication.setApplicant(null);
				jobApplicationDao.createJobApplication(jobApplication);
			}
			applicantDao.deleteApplicant(applicant);

			Resume resume = applicant.getResume();

			if (resume != null) {
				resume.setSkills(null);
				resumeDao.saveResume(resume);
				for (Project project : applicant.getResume().getProjects()) {
					projectDao.deleteProject(project);
				}

				resumeDao.deleteResume(resume);
			}

			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant Deleted Successfully!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);

		} else {

			throw new ApplicantNotFoundByIdException("Failed to delete  Applicant!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<ApplicantResponse>>> getApplicantBySkill(String skill) {
		Optional<Skill> optional = applicantDao.getSkillBySkillName(skill);
		if (optional.isPresent()) {
			Skill exSkill = optional.get();
			List<ApplicantResponse> responses = new ArrayList<>();
			for (Resume resume : exSkill.getResumes()) {
				ApplicantResponse response = this.modelMapper.map(resume.getApplicant(), ApplicantResponse.class);
				responses.add(response);

			}
			ResponseStructure<List<ApplicantResponse>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant not found by Skill!");
			responseStructure.setData(responses);
			return new ResponseEntity<ResponseStructure<List<ApplicantResponse>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new SkillNotFoundByNameException("Failed to find Applicants!");
		}
	}
}
