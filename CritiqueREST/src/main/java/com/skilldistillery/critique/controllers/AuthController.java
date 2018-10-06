package com.skilldistillery.critique.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.User;
import com.skilldistillery.critique.services.AuthService;

@RestController
//@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"})
public class AuthController {
	@Autowired
	private AuthService authServ;
	
//	@RequestMapping(path="register/ping")
//	public String ping() {
//		return "pong";
//	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public Profile register(@RequestBody String json, HttpServletResponse res) {
	  Profile p =  authServ.register(json);

	  if (p == null) {
	    res.setStatus(400);
	  }

	  return p;
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public Principal authenticate(Principal principal) {
	  return principal;
	}

}
