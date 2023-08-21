package edu.training.jobportalapplication.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.jobportalapplication.dao.EmployerDao;
import edu.training.jobportalapplication.dao.JobApplicationDao;
import edu.training.jobportalapplication.dao.JobDao;
import edu.training.jobportalapplication.dao.SkillDao;
import edu.training.jobportalapplication.dto.JobDto;
import edu.training.jobportalapplication.dto.JobResponse;
import edu.training.jobportalapplication.entity.Employer;
import edu.training.jobportalapplication.entity.Job;
import edu.training.jobportalapplication.entity.JobApplication;
import edu.training.jobportalapplication.entity.Skill;
import edu.training.jobportalapplication.exception.EmployerNotFoundByIdExceeption;
import edu.training.jobportalapplication.exception.JobNotFoundByIdException;
import edu.training.jobportalapplication.exception.JobNotFoundBySkillException;
import edu.training.jobportalapplication.exception.SkillNotFoundByIdException;
import edu.training.jobportalapplication.util.ResponseStructure;

@Service
public class JobService {

	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployerDao employerDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SkillDao skillDao;

	@Autowired
	private JobApplicationDao applicationDao;

	public ResponseEntity<ResponseStructure<JobResponse>> addJob(JobDto jobDto, long employerId, String[] skills) {

		Employer employer = employerDao.getEmployerById(employerId);
		if (employer != null) {

			Job job = this.modelMapper.map(jobDto, Job.class);

			job.setJobCreateDateTime(LocalDateTime.now());
			job.setEmployer(employer);

			jobDao.addJob(job);

			List<Skill> jobSkills = new ArrayList<>();

			for (String skill : skills) {
				Skill existingSkill = skillDao.getSkillByName(skill);

				if (existingSkill != null) {
					if (!jobSkills.contains(existingSkill)) {
						jobSkills.add(existingSkill);
					}
				} else {
					Skill newSkill = new Skill();
					newSkill.setSkillName(skill);
					skillDao.saveSkill(newSkill);
					jobSkills.add(newSkill);

				}
			}

			job.setSkills(jobSkills);
			employer.getJobs().add(job);
			employerDao.addEmployer(employer);

			JobResponse response = this.modelMapper.map(job, JobResponse.class);
			List<String> responseSkills = new ArrayList<>();
			for (Skill skill : job.getSkills()) {
				responseSkills.add(skill.getSkillName());
			}
			response.setSkills(responseSkills);

			jobDto.setJobId(job.getJobId());

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job adeed Successfully");
			responseStructure.setData(job);

			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.ACCEPTED.CREATED);

		} else {
			throw new EmployerNotFoundByIdExceeption("Failed to add Job!");
		}
	}

	public ResponseEntity<ResponseStructure<JobResponse>> getJobById(long jobId) {
		Job job = jobDao.getJobById(jobId);

		if (job != null) {

			JobResponse response = this.modelMapper.map(job, JobResponse.class);
			List<String> responseSkills = new ArrayList<>();
			for (Skill skill : job.getSkills()) {
				responseSkills.add(skill.getSkillName());
			}
			response.setSkills(responseSkills);

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Found!");
			responseStructure.setData(response);

			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.FOUND);

		} else {

			throw new JobNotFoundByIdException("Failed to find Job with this Id!");
		}
	}

	public ResponseEntity<ResponseStructure<List<JobResponse>>> getJobsBySkill(long skillId) {

		Skill skill = skillDao.getSkillById(skillId);
		if (skill != null) {
			List<Job> jobs = skill.getJobs();
			List<JobResponse> responses = new ArrayList<>();

			if (!jobs.isEmpty()) {
				for (Job job : jobs) {
					JobResponse response = this.modelMapper.map(job, JobResponse.class);
					List<String> skills = new ArrayList<>();
					for (Skill s : job.getSkills()) {
						skills.add(s.getSkillName());
					}
					response.setSkills(skills);
					responses.add(response);
				}

				ResponseStructure<List<JobResponse>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Job Found!");
				responseStructure.setData(responses);

				return new ResponseEntity<ResponseStructure<List<JobResponse>>>(responseStructure, HttpStatus.FOUND);

			} else {
				throw new JobNotFoundBySkillException("Failed to find job!!");
			}
		} else {
			throw new SkillNotFoundByIdException("Failed to fond job!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobResponse>> updateJobById(JobDto jobDto, long jobId) {
		Job exJob = jobDao.getJobById(jobId);

		if (exJob != null) {
			Job job = this.modelMapper.map(jobDto, Job.class);
			job.setJobId(jobId);
			job.setJobCreateDateTime(exJob.getJobCreateDateTime());
			job.setEmployer(exJob.getEmployer());
			job.setJobApplications(exJob.getJobApplications());
			job.setSkills(exJob.getSkills());
			job = jobDao.addJob(job);

			JobResponse response = this.modelMapper.map(job, JobResponse.class);
			List<String> skills = new ArrayList<>();
			for (Skill skill : job.getSkills()) {
				skills.add(skill.getSkillName());
			}
			response.setSkills(skills);

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Job updated successfully!");
			responseStructure.setData(response);

			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.OK);
		} else {
			throw new JobNotFoundByIdException("Job not found By Id!!");
		}

	}

	public ResponseEntity<ResponseStructure<JobResponse>> deleteJobById(long jobId) {
		Job job = jobDao.getJobById(jobId);
		if (job != null) {
			for (JobApplication application : job.getJobApplications()) {
				application.setJob(null);
				applicationDao.createJobApplication(application);
			}
			JobResponse response = this.modelMapper.map(job, JobResponse.class);
			List<String> responseSkills = new ArrayList<>();
			for (Skill skill : job.getSkills()) {
				responseSkills.add(skill.getSkillName());
			}
			response.setSkills(responseSkills);
			jobDao.deleteJob(job);

			ResponseStructure<JobResponse> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Job deleted successfully");
			responseStructure.setData(response);
			return new ResponseEntity<ResponseStructure<JobResponse>>(responseStructure, HttpStatus.OK);

		} else {

			throw new JobNotFoundByIdException("Failed to delete job");
		}

	}
}
