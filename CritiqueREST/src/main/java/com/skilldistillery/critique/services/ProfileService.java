package com.skilldistillery.critique.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.skilldistillery.critique.entities.Profile;

public interface ProfileService {
	public List<Profile> findByFirstname(String firstName);
	public List<Profile> findByLastname(String lastName);
	public List<Profile> findByFirstNameAndLastName(String fname, String lname);
	public List<Profile> queryByCityWithLocation(String city);
	public List<Profile> queryByStateWithLocation(String state);
	public List<Profile> queryByCountryWithLocation(String country);
	public Profile queryByUsernameWithUser(String username);
	public Profile findProfileById(int id);
	public Profile create(Profile createdProfile);
	public Profile update(int id, Profile updatedProfile);
	public boolean delete(int id);
}
