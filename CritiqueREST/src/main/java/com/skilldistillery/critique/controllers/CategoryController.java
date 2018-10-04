package com.skilldistillery.critique.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Category;
import com.skilldistillery.critique.services.CategoryService;

@RestController
@RequestMapping("api")
public class CategoryController {
	@Autowired
	private CategoryService catServ;
	
	@RequestMapping(path = "categories", method = RequestMethod.GET)
	public List<Category> indexCategories(HttpServletRequest req, HttpServletResponse res) {
		List<Category> categories = catServ.index();
		if (categories != null) {
			res.setStatus(200);
			return categories;
		} else {
			res.setStatus(500);
			return null;
		}
	}

}
