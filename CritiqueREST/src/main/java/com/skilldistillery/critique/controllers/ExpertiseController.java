package com.skilldistillery.critique.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Expertise;
import com.skilldistillery.critique.services.ExpertiseService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"})
public class ExpertiseController {
	
	@Autowired
	private ExpertiseService exServ;
	
	@RequestMapping(path = "skills", method = RequestMethod.GET)
	public List<Expertise> index(HttpServletRequest req, HttpServletResponse res) {
		List<Expertise> skills = exServ.index();
		if (skills != null) {
			res.setStatus(200);
			return skills;
		} else {
			res.setStatus(500);
			return null;
		}
	}

}
