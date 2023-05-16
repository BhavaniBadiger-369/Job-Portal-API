package edu.training.jobportalapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.jobportalapplication.entity.Applicant;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {

}
