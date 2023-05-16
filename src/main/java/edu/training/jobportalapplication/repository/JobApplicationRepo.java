package edu.training.jobportalapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.jobportalapplication.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {

}
