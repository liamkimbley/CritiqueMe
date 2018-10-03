package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
