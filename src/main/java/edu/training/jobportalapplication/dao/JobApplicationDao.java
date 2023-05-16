package edu.training.jobportalapplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.repository.JobApplicationRepo;

@Repository
public class JobApplicationDao {

	@Autowired
	private JobApplicationRepo jobApplicationRepo;
}
