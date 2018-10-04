package com.skilldistillery.critique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.services.ProfileService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"})
public class ProfileController {
	@Autowired
	private ProfileService ps;
	
	@RequestMapping(path = "profile/{firstName}", method = RequestMethod.GET)
	public List<Profile> findByFirstname(@PathVariable String firstName) {
		return ps.findByFirstname(firstName);
	}
	
	@RequestMapping(path = "profile/{lastName}", method = RequestMethod.GET)
	public List<Profile> findByLastname(@PathVariable String lastName) {
		return ps.findByFirstname(lastName);
	}
	
	@RequestMapping(path = "profile/{firstName}{lastName}", method = RequestMethod.GET)
	public List<Profile> findByFirstNameAndLastName(@PathVariable String fname, @PathVariable String lname) {
		return ps.findByFirstNameAndLastName(fname, lname);
	}
	
	@RequestMapping(path = "profile/{city}", method = RequestMethod.GET)
	public List<Profile> findByCityWithLocation(@PathVariable String city) {
		return ps.queryByCityWithLocation(city);
	}
	
	@RequestMapping(path = "profile/{state}", method = RequestMethod.GET)
	public List<Profile> findByStateWithLocation(@PathVariable String state) {
		return ps.queryByCityWithLocation(state);
	}
	
	@RequestMapping(path = "profile/{country}", method = RequestMethod.GET)
	public List<Profile> findByCountryWithLocation(@PathVariable String country) {
		return ps.queryByCityWithLocation(country);
	}
	
	// create when user creates an account
	@RequestMapping(path = "profile", method = RequestMethod.POST)
	public Profile createProfile(@RequestBody Profile p) {
		return ps.create(p);
	}
	
	@RequestMapping(path = "profile/{id}", method = RequestMethod.PATCH)
	public Profile updateProfile(@RequestParam Profile p, @PathVariable int id) {
		return ps.update(id, p);
	}
	
	@RequestMapping(path = "profile/{id}", method = RequestMethod.DELETE)
	public boolean deleteProfile(@PathVariable int id) {
		return ps.delete(id);
	}

	
}
