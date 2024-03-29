package edu.training.jobportalapplication.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.training.jobportalapplication.entity.Applicant;
import edu.training.jobportalapplication.entity.Skill;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {

	@Query(value="select s from Skill s where s.skillName=?1 ")
	public Optional<Skill> getSkillBySkillName(String skill);
}
