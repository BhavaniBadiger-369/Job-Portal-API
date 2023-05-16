package edu.training.jobportalapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.jobportalapplication.entity.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Long>{

}
