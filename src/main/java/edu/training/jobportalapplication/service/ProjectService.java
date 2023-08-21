package edu.training.jobportalapplication.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.ApplicantDao;
import edu.training.jobportalapplication.dao.ProjectDao;
import edu.training.jobportalapplication.dao.ResumeDao;
import edu.training.jobportalapplication.dto.ProjectDto;
import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.entity.Project;
import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.exception.ApplicantNotFoundByIdException;
import edu.training.jobportalapplication.exception.ProjectNotFoundByIdException;
import edu.training.jobportalapplication.exception.ResumeNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Resume>> saveProject(long applicantId, ProjectDto projectDto) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {

			Resume resume = applicant.getResume();

			if (resume != null) {

				List<Project> exProjects = resume.getProjects();
				Project project = this.modelMapper.map(projectDto, Project.class);

				project = projectDao.saveProject(project);

				exProjects.add(project);
				resumeDao.saveResume(resume);

				ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Projects Added Successfully!");
				responseStructure.setData(resume);

				return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);

			} else {
				throw new ResumeNotFoundByIdException("Failed to add projects!");
			}

		} else {

			throw new ApplicantNotFoundByIdException("Failed to add projects!");
		}
	}

	public ResponseEntity<ResponseStructure<Project>> getProject(long projectId) {

		Optional<Project> optional = projectDao.findProjectById(projectId);
		if (optional.isPresent()) {

			ResponseStructure<Project> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Project found!");
			responseStructure.setData(optional.get());

			return new ResponseEntity<ResponseStructure<Project>>(responseStructure, HttpStatus.FOUND);

		} else {

			throw new ProjectNotFoundByIdException("Failed to find Project");
		}

	}

	public ResponseEntity<ResponseStructure<Project>> updateProject(long projectId, ProjectDto projectDto) {

		Optional<Project> optional = projectDao.findProjectById(projectId);

		if (optional.isPresent()) {

			Project project = this.modelMapper.map(projectDto, Project.class);

			project.setProjectId(projectId);

			project = projectDao.saveProject(project);

			ResponseStructure<Project> responsestructure = new ResponseStructure<>();
			responsestructure.setStatusCode(HttpStatus.OK.value());
			responsestructure.setMessage("Project updated successfully");
			responsestructure.setData(optional.get());

			return new ResponseEntity<ResponseStructure<Project>>(responsestructure, HttpStatus.OK);

		} else {
			throw new ProjectNotFoundByIdException("Failed to update project!");
		}
	}

	public ResponseEntity<ResponseStructure<Project>> deleteProject(long projectId, long applicantId) {

		Optional<Project> optional = projectDao.findProjectById(projectId);

		if (optional.isPresent()) {

			Applicant applicant = applicantDao.getApplicantById(applicantId);

			if (applicant != null) {
				Resume resume = applicant.getResume();

				if (resume != null) {
					resume.getProjects().remove(optional.get());

					resumeDao.saveResume(resume);
				}

				projectDao.deleteProject(optional.get());

				ResponseStructure<Project> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Project deleted successfully");
				responseStructure.setData(optional.get());

				return new ResponseEntity<ResponseStructure<Project>>(responseStructure, HttpStatus.OK);

			}

			else {
				throw new ApplicantNotFoundByIdException("failed to delete project");

			}
		} else {
			throw new ProjectNotFoundByIdException("Failed to delete Project");
		}

	}
}
