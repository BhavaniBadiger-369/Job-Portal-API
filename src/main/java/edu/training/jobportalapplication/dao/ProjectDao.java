package edu.training.jobportalapplication.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.jobportalapplication.entity.Project;
import edu.training.jobportalapplication.repository.ProjectRepo;

@Repository
public class ProjectDao {

	@Autowired
	private ProjectRepo projectRepo;

	public Project saveProject(Project project) {
		return projectRepo.save(project);
	}

	public Optional<Project> findProjectById(long projectId) {
		return projectRepo.findById(projectId);

	}

	public Project updateProject(long projectId, Project project) {
		Optional<Project> optional = projectRepo.findById(projectId);
		if (optional.isEmpty()) {
			return null;
		} else {
			project.setProjectId(projectId);
			return projectRepo.save(project);
		}
	}

	public void deleteProject(Project project) {
		projectRepo.delete(project);
	}
}
