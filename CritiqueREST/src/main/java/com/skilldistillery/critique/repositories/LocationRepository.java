package com.skilldistillery.critique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

	public List<Location> findByCity(String city);
	public List<Location> findByState(String state);
	public List<Location> findByCountry(String country);
}
