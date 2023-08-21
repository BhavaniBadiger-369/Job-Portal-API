package edu.training.jobportalapplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.entity.Job;
import edu.training.jobportalapplication.repository.JobRepo;

@Repository
public class JobDao {

	@Autowired
	private JobRepo jobRepo;

	public Job addJob(Job job) {
		return jobRepo.save(job);
	}

	public Job getJobById(long jobId) {

		Optional<Job> optional = jobRepo.findById(jobId);

		if (optional.isEmpty()) {
			return null;

		} else {

			return optional.get();

		}
	}

	public Job updateJob(long jobId, Job job) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if (optional.isEmpty()) {
			return null;
		} else {
			job.setJobId(jobId);
			return jobRepo.save(job);
		}
	}

	public void deleteJob(Job job) {
		jobRepo.delete(job);
	}
}
