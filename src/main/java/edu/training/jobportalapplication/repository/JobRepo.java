package edu.training.jobportalapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.jobportalapplication.entity.Job;

public interface JobRepo extends JpaRepository<Job, Long>{

}
