package com.skilldistillery.critique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.repositories.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepository profRepo;

	@Override
	public List<Profile> findByFirstname(String firstName) {
		return profRepo.findByFirstname(firstName);
	}

	@Override
	public List<Profile> findByLastname(String lastName) {
		return profRepo.findByLastname(lastName);
	}

	@Override
	public List<Profile> findByFirstNameAndLastName(String fname, String lname) {
		return profRepo.findByFirstNameAndLastName(fname, lname);
	}
	
	@Override
	public List<Profile> findByFirstNameContainingOrLastNameContaining(String name) {
		String nameContaining = "%" + name + "%";
		return profRepo.findByFirstNameContainingOrLastNameContaining(nameContaining);
	}


	@Override
	public List<Profile> queryByCityWithLocation(String city) {
		return profRepo.queryByCityWithLocation(city);
	}

	@Override
	public List<Profile> queryByStateWithLocation(String state) {
		return profRepo.queryByStateWithLocation(state);
	}

	@Override
	public List<Profile> queryByCountryWithLocation(String country) {
		return profRepo.queryByCountryWithLocation(country);
	}
	
	
}
