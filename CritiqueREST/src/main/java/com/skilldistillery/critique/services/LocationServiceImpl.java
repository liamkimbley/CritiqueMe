package com.skilldistillery.critique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Location;
import com.skilldistillery.critique.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locRepo;

	@Override
	public List<Location> findLocationByCity(String city) {
		return locRepo.findByCity(city);

	}

	@Override
	public List<Location> findLocationByState(String state) {
		return locRepo.findByState(state);

	}

	@Override
	public List<Location> findLocationByCountry(String country) {
		return locRepo.findByCountry(country);

	}

}
