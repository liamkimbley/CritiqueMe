package com.skilldistillery.critique.services;

import java.util.List;
import java.util.Optional;

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
		return profRepo.findByFirstName(firstName);
	}

	@Override
	public List<Profile> findByLastname(String lastName) {
		return profRepo.findByLastName(lastName);
	}

	@Override
	public List<Profile> findByFirstNameAndLastName(String fname, String lname) {
		return profRepo.findByFirstNameAndLastName(fname, lname);
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
	
	@Override
	public Profile queryByUsernameWithUser(String username) {
		return profRepo.queryByUsernameWithUser(username);
	}
	
	@Override
	public Profile findProfileById(int id) {
		Profile profile = null;
		Optional<Profile> p = profRepo.findById(id);
		if (p.isPresent()) {
			profile = p.get();
		}
		return profile;
	}
	
	//
	public Profile create(Profile createdProfile) {
		Profile newProfile = new Profile();
		newProfile.setFirstName(createdProfile.getFirstName());
		newProfile.setLastName(createdProfile.getLastName());
		if (createdProfile.getBio() != null) {
			newProfile.setBio(createdProfile.getBio());
		}
		if (createdProfile.getLocation() != null) {
			newProfile.setLocation(createdProfile.getLocation());
		}
		newProfile.setUser((createdProfile.getUser()));
		return profRepo.saveAndFlush(newProfile);
	}
	
	public Profile update(int id, Profile updatedProfile) {
		Optional<Profile> op = profRepo.findById(id);
		
		if (op.isPresent()) {
			Profile profile = op.get();
			if (updatedProfile.getFirstName() != null) {
				profile.setFirstName(updatedProfile.getFirstName());
			}
			if (updatedProfile.getLastName() != null) {
				profile.setLastName(updatedProfile.getLastName());
			}
			if (updatedProfile.getBio() != null) {
				profile.setBio(updatedProfile.getBio());
			}
			if (updatedProfile.getLocation() != null) {
				profile.setLocation(updatedProfile.getLocation());
			}
			if (updatedProfile.getUser() != null) {
				profile.setUser(updatedProfile.getUser());
			}
			if (updatedProfile.getImageUrl() != null) {
				profile.setImageUrl(updatedProfile.getImageUrl());
			}
			if (updatedProfile.getSkills() != null) {
				profile.setSkills(updatedProfile.getSkills());
			}
			return profRepo.saveAndFlush(profile); 
		}
		else {
			return null;
		}
	}
	
	public boolean delete(int id) {
		try {
			profRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
