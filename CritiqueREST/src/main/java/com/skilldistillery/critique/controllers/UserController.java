package com.skilldistillery.critique.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(path = "users", method = RequestMethod.GET)
	public List<User> indexUsers(HttpServletRequest req, HttpServletResponse res) {
		List<User> users = us.index();
		if(users != null) {
			res.setStatus(200);
			return users;
		} else {
			res.setStatus(500);
			return null;
		}
	}
	
	@RequestMapping(path = "users/{id}", method = RequestMethod.GET)
	public User showUser(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse res) {
		User user = us.show(id);
		if(user != null) {
			res.setStatus(200);
			return user;
		} else {
			res.setStatus(500);
			return null;
		}
	}
	
	@RequestMapping(path="users/search/username/{username}", method = RequestMethod.GET) 
	public User findOneUserByUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse res) {
		User u = us.findOneUserByUsername(username);
		if (u != null) {
			res.setStatus(200);
			return u;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path = "users/search/email/{email}", method = RequestMethod.GET)
	public User findByEmail(@PathVariable String email, HttpServletRequest req, HttpServletResponse res) {
		User u = us.findByEmail(email);
		if (u != null) {
			res.setStatus(200);
			return u;
		} else {
			res.setStatus(500);
			return null;
		}
	}
	
	@RequestMapping(path = "users/search/active/{active}", method = RequestMethod.GET)
	public List<User> findByActive(@PathVariable Boolean active, HttpServletRequest req, HttpServletResponse res) {
		List<User> users = us.findByActive(active);
		if (users != null) {
			res.setStatus(200);
			return users;
		}
		else {
			res.setStatus(500);
			return null;
		}
	}
	
}
