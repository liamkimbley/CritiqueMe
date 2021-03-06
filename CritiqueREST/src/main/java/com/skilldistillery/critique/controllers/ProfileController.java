package com.skilldistillery.critique.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.services.PostService;
import com.skilldistillery.critique.services.ProfileService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4201" })
public class ProfileController {
	@Autowired
	private ProfileService ps;

	@Autowired
	private PostService postServ;

	@RequestMapping(path = "profile/firstname/{firstName}", method = RequestMethod.GET)
	public List<Profile> findByFirstname(@PathVariable String firstName, HttpServletResponse res,
			HttpServletRequest req) {
		List<Profile> profiles = ps.findByFirstname(firstName);
		if (profiles != null) {
			res.setStatus(200);
			return profiles;
		} else {
			res.setStatus(204);
			return null;
		}
	}

	@RequestMapping(path = "profile/lastname/{lastName}", method = RequestMethod.GET)
	public List<Profile> findByLastname(@PathVariable String lastName, HttpServletResponse res,
			HttpServletRequest req) {
		List<Profile> profiles = ps.findByLastname(lastName);
		if (profiles != null) {
			res.setStatus(200);
			return profiles;
		} else {
			res.setStatus(204);
			return null;
		}
	}

	@RequestMapping(path = "profile/fullname/{firstName}/{lastName}", method = RequestMethod.GET)
	public List<Profile> findByFirstNameAndLastName(@PathVariable String fname, @PathVariable String lname,
			HttpServletResponse res, HttpServletRequest req) {
		List<Profile> profiles = ps.findByFirstNameAndLastName(fname, lname);
		if (profiles != null) {
			res.setStatus(200);
			return profiles;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path = "profile/city/{city}", method = RequestMethod.GET)
	public List<Profile> findByCityWithLocation(@PathVariable String city, HttpServletResponse res,
			HttpServletRequest req) {
		List<Profile> profiles = ps.queryByCityWithLocation(city);
		if (profiles != null) {
			res.setStatus(200);
			return profiles;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path = "profile/state/{state}", method = RequestMethod.GET)
	public List<Profile> findByStateWithLocation(@PathVariable String state, HttpServletResponse res,
			HttpServletRequest req) {
		List<Profile> profiles = ps.queryByStateWithLocation(state);
		if (profiles != null) {
			res.setStatus(200);
			return profiles;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path = "profile/country/{country}", method = RequestMethod.GET)
	public List<Profile> findByCountryWithLocation(@PathVariable String country, HttpServletResponse res,
			HttpServletRequest req) {
		List<Profile> profiles = ps.queryByCountryWithLocation(country);
		if (profiles != null) {
			res.setStatus(200);
			return profiles;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path = "profile/user/{username}", method = RequestMethod.GET)
	public Profile findByUsernameWithUser(@PathVariable String username, HttpServletResponse res,
			HttpServletRequest req) {
		Profile prof = ps.queryByUsernameWithUser(username);
		if (prof != null) {
			res.setStatus(200);
			return prof;
		} else {
			res.setStatus(204);
			return null;
		}
	}

	@RequestMapping(path = "profile", method = RequestMethod.GET)
	public Profile findProfile(Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Profile prof = ps.queryByUsernameWithUser(principal.getName());
//		System.out.println("===================================================");
//		System.out.println(prof);
//		System.out.println(principal);
		if (prof != null) {
			res.setStatus(200);
			return prof;
		} else {
			res.setStatus(204);
			return null;
		}
	}

	@RequestMapping(path = "profile/{pid}", method = RequestMethod.GET)
	public Profile findOneProfile(@PathVariable("pid") Integer pid, HttpServletResponse res, HttpServletRequest req) {
		Profile prof = ps.findProfileById(pid);
		if (prof != null) {
			res.setStatus(200);
			return prof;
		} else {
			res.setStatus(204);
			return null;
		}
	}

	@RequestMapping(path = "profile/{profid}/posts", method = RequestMethod.GET)
	public List<Post> findPostsByProfile(@PathVariable Integer profid, Principal principal, HttpServletResponse res,
			HttpServletRequest req) {
		List<Post> posts = new ArrayList<>();
		Profile prof = ps.findProfileById(profid);
		posts = postServ.findPostsByProfileId(profid);

		if (posts.isEmpty()) {
			res.setStatus(204);
			return null;
		}
		res.setStatus(200);
		return posts;
	}

	@RequestMapping(path = "profile/{id}", method = RequestMethod.PUT)
	public Profile updateProfile(@RequestBody Profile p, @PathVariable Integer id, HttpServletResponse res,
			HttpServletRequest req) {
		Profile prof = ps.update(id, p);
		if (prof != null) {
			res.setStatus(200);
			return prof;
		} else {
			res.setStatus(204);
			return null;
		}
	}

	@RequestMapping(path = "profile/{id}", method = RequestMethod.PATCH)
	public Boolean deleteProfile(@PathVariable Integer id, HttpServletResponse res, HttpServletRequest req) {
		Boolean deletedProfile = ps.delete(id);
		if (deletedProfile == true) {
			res.setStatus(204);
			return true;
		} else {
			res.setStatus(500);
			return false;
		}
	}

}
