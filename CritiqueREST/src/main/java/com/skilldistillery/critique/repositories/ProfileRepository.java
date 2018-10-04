package com.skilldistillery.critique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.critique.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	@Query("SELECT p FROM Profile p WHERE p.firstName = :fname AND p.lastName = :lname")
	public List<Profile> findByFirstNameAndLastName(@Param("fname") String fname, @Param("lname") String lname);
}
