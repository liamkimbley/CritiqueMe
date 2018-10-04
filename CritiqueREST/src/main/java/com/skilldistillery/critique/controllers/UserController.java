package com.skilldistillery.critique.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.User;
import com.skilldistillery.critique.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"})
public class UserController {
	@Autowired
	private UserService us;
	
	@RequestMapping(path="users/{username}", method = RequestMethod.GET) 
	public User findOneUserByUsername(@PathVariable String username) {
		return us.findOneUserByUsername(username);
	}

	@RequestMapping(path = "users/{email}", method = RequestMethod.GET)
	public User findByEmail(@PathVariable String email) {
		return us.findByEmail(email);
	}
	
	@RequestMapping(path = "users/{active}", method = RequestMethod.GET)
	public List<User> findByActive(@PathVariable boolean active) {
		return us.findByActive(active);
	}
	
}
