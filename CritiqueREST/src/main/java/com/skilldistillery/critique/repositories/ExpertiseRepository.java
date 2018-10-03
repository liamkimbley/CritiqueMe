package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Expertise;

public interface ExpertiseRepository extends JpaRepository<Expertise, Integer> {

}
