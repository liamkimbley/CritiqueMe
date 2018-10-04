package com.skilldistillery.critique.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Location;
import com.skilldistillery.critique.services.LocationService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"})
public class LocationController {
	
	@Autowired
	private LocationService locServ;
	
	@RequestMapping(path = "locations", method = RequestMethod.GET)
	public List<Location> indexLocations(HttpServletRequest req, HttpServletResponse res) {
		List<Location> locations = locServ.index();
		if (locations != null) {
			res.setStatus(200);
			return locations;
		} else {
			res.setStatus(500);
			return null;
		}
	}

}
