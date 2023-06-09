package edu.training.jobportalapplication.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.training.jobportalapplication.entity.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long>{

	@Query(value = "select s  from Skill s where s.skillName=?1")
	public Optional<Skill> getSkillByName(String skillName);
}
