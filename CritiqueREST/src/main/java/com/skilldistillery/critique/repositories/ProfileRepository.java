package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

}
