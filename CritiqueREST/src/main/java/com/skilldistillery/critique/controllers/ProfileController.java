package com.skilldistillery.critique.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.services.ProfileService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"})
public class ProfileController {
	@Autowired
	private ProfileService ps;
	
	@RequestMapping(path = "profile/firstname/{firstName}", method = RequestMethod.GET)
	public List<Profile> findByFirstname(@PathVariable String firstName) {
		return ps.findByFirstname(firstName);
	}
	
	@RequestMapping(path = "profile/lastname/{lastName}", method = RequestMethod.GET)
	public List<Profile> findByLastname(@PathVariable String lastName) {
		return ps.findByLastname(lastName);
	}
	
	@RequestMapping(path = "profile/fullname/{firstName}/{lastName}", method = RequestMethod.GET)
	public List<Profile> findByFirstNameAndLastName(@PathVariable String fname, @PathVariable String lname) {
		return ps.findByFirstNameAndLastName(fname, lname);
	}
	
	@RequestMapping(path = "profile/city/{city}", method = RequestMethod.GET)
	public List<Profile> findByCityWithLocation(@PathVariable String city) {
		return ps.queryByCityWithLocation(city);
	}
	
	@RequestMapping(path = "profile/state/{state}", method = RequestMethod.GET)
	public List<Profile> findByStateWithLocation(@PathVariable String state) {
		return ps.queryByStateWithLocation(state);
	}
	
	@RequestMapping(path = "profile/country/{country}", method = RequestMethod.GET)
	public List<Profile> findByCountryWithLocation(@PathVariable String country) {
		return ps.queryByCountryWithLocation(country);
	}
	
	@RequestMapping(path = "profile/user/{username}", method = RequestMethod.GET)
	public Profile findByUsernameWithUser(@PathVariable String username, HttpServletResponse res) {
		Profile prof = ps.queryByUsernameWithUser(username);
		if (prof != null) {
			res.setStatus(200);
			return prof;
		} else {
			res.setStatus(500);
			return null;
		}
	}
	
	@RequestMapping(path = "profile/{id}", method = RequestMethod.GET)
	public Profile findProfileById(@PathVariable int id) {
		return ps.findProfileById(id);
	}
	
	// create when user creates an account
//	@RequestMapping(path = "profile", method = RequestMethod.POST)
//	public Profile createProfile(@RequestBody Profile p, HttpServletResponse res) {
//		Profile prof = ps.create(p);
//		if (prof != null) {
//			res.setStatus(200);
//			return prof;
//		} else {
//			res.setStatus(500);
//			return null;
//		}
//	}
	
	@RequestMapping(path = "profile/{id}", method = RequestMethod.PATCH)
	public Profile updateProfile(@RequestBody Profile p, @PathVariable int id, HttpServletResponse res) {
		Profile prof = ps.update(id, p);
		if (prof != null) {
			res.setStatus(200);
			return prof;
		} else {
			res.setStatus(500);
			return null;
		}
	}
	
	@RequestMapping(path = "profile/{id}", method = RequestMethod.DELETE)
	public boolean deleteProfile(@PathVariable int id) {
		return ps.delete(id);
	}

	
}
