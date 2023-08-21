package edu.training.jobportalapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training.jobportalapplication.dto.ProjectDto;
import edu.training.jobportalapplication.entity.Project;
import edu.training.jobportalapplication.entity.Resume;
import edu.training.jobportalapplication.service.ProjectService;
import edu.training.jobportalapplication.util.ResponseStructure;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired

	ProjectService projectService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> saveProject(@RequestParam long projectId,
			@RequestBody ProjectDto projectDto) {
		return projectService.saveProject(projectId, projectDto);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Project>> getProject(@RequestParam long projectId) {
		return projectService.getProject(projectId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Project>> updateProject(@RequestParam long projectId,
			@RequestBody ProjectDto projectDto) {
		return projectService.updateProject(projectId, projectDto);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Project>> deleteProject(@RequestParam long projectId,
			@RequestParam long applicantId) {
		return projectService.deleteProject(projectId, applicantId);
	}
}
