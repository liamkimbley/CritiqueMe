package com.skilldistillery.critique.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Expertise;
import com.skilldistillery.critique.entities.Location;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.User;
import com.skilldistillery.critique.repositories.ProfileRepository;
import com.skilldistillery.critique.repositories.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepository profRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private LocationService locServ;
	
	@Autowired
	private ExpertiseService expServ;
	

	@Override
	public List<Profile> findByFirstname(String firstName) {
		List<Profile> profiles = profRepo.findByFirstName(firstName);
		if (profiles.isEmpty() ) {
			return null;
		}
		return profiles;
	}

	@Override
	public List<Profile> findByLastname(String lastName) {
		List<Profile> profiles = profRepo.findByLastName(lastName);
		if (profiles.isEmpty() ) {
			return null;
		}
		return profiles;
	}

	@Override
	public List<Profile> findByFirstNameAndLastName(String fname, String lname) {
		List<Profile> profiles = profRepo.findByFirstNameAndLastName(fname, lname);
		if (profiles.isEmpty() ) {
			return null;
		}
		return profiles;
		
	}

	@Override
	public List<Profile> queryByCityWithLocation(String city) {
		List<Profile> profiles = profRepo.queryByCityWithLocation(city);
		if (profiles.isEmpty() ) {
			return null;
		}
		return profiles;
	}

	@Override
	public List<Profile> queryByStateWithLocation(String state) {
		List<Profile> profiles = profRepo.queryByStateWithLocation(state);
		if (profiles.isEmpty() ) {
			return null;
		}
		return profiles;
	}

	@Override
	public List<Profile> queryByCountryWithLocation(String country) {
		List<Profile> profiles = profRepo.queryByCountryWithLocation(country);
		if (profiles.isEmpty() ) {
			return null;
		}
		return profiles;
	}
	
	@Override
	public Profile queryByUsernameWithUser(String username) {
		Profile profile = profRepo.queryByUsernameWithUser(username);
		if(profile == null) {
			return null;
		}
		return profile;
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
			User u = userRepo.findById(updatedProfile.getUser().getId()).get();
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
			if (updatedProfile.getLocation() == null) {
				Location newLocation = new Location();
				newLocation.setCity("Somewhere");
				newLocation.setState("Overtherainbow");
				newLocation.setCountry("Oz");
				profile.setLocation(locServ.create(newLocation));
			}
			if (updatedProfile.getUser() != null) {
				profile.setUser(updatedProfile.getUser());
			}
			if (updatedProfile.getImageUrl() != null) {
				profile.setImageUrl(updatedProfile.getImageUrl());
			}
			if (updatedProfile.getSkills() != null && !updatedProfile.getSkills().isEmpty()) {
				profile.setSkills(updatedProfile.getSkills());
			}
			if (updatedProfile.getSkills() == null || updatedProfile.getSkills().isEmpty()) {
				profile.setSkills(new ArrayList<Expertise>());
			}
			if (updatedProfile.getUser() != null) {
				profile.setUser(u);
			}
			return profRepo.saveAndFlush(profile); 
		}
		else {
			return null;
		}
	}
	
	public boolean delete(int id) {
		try {
			User u = userRepo.findById(id).get();
			u.setActive(false);
			userRepo.saveAndFlush(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
