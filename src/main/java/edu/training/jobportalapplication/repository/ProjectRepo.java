package edu.training.jobportalapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.jobportalapplication.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
