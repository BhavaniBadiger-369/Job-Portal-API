package edu.training.jobportalapplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.repository.ProjectRepo;

@Repository
public class ProjectDao {

	@Autowired
	private ProjectRepo projectRepo;
}
