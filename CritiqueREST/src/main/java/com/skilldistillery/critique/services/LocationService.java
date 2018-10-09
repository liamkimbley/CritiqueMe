package com.skilldistillery.critique.services;

import java.util.List;

import com.skilldistillery.critique.entities.Location;

public interface LocationService {
	
	public List<Location> index();
	public List<Location> findLocationByCity(String city);
	public List<Location> findLocationByState(String state);
	public List<Location> findLocationByCountry(String country);
	public Location create(Location location);

}
