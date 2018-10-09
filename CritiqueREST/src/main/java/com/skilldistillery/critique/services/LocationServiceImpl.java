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
	public List<Location> index() {
		List<Location> locations = locRepo.findAll();
		if (locations.isEmpty()) {
			return null;
		}
		return locations;
	}

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

	@Override
	public Location create(Location location) {
		Location newLoc = new Location();
		newLoc.setCity(location.getCity());
		newLoc.setCountry(location.getCountry());
		newLoc.setState(location.getState());
		locRepo.saveAndFlush(newLoc);
		return newLoc;
	}

}
