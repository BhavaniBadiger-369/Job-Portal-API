package edu.training.jobportalapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.jobportalapplication.entity.Resume;

public interface ResumeRepo extends JpaRepository<Resume, Long>{

}
