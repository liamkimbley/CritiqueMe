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

import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.services.PostService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4201" })
public class PostController {

	@Autowired
	private PostService postServ;

	@RequestMapping(path = "posts", method = RequestMethod.GET)
	public List<Post> index() {
		return postServ.findAllPosts();
	}

	@RequestMapping(path = "categories/{id}/posts", method = RequestMethod.GET)
	public List<Post> postByCategory(@PathVariable Integer id) {
		return postServ.findPostsByCategoryId(id);
	}

	@RequestMapping(path = "posts/{pid}", method = RequestMethod.GET)
	public Post getSinglePost(@PathVariable Integer pid) {
		return postServ.findPostById(pid);
	}

	@RequestMapping(path = "posts", method = RequestMethod.POST)
	public Post create(@RequestBody Post post, HttpServletResponse res) {
		Post p = postServ.create(post);
		if (p != null) {
			res.setStatus(201);
		} else {
			res.setStatus(500);
		}
		return p;
	}

	@RequestMapping(path = "posts/{pid}", method = RequestMethod.PUT)
	public Post replacePost(@PathVariable Integer pid, @RequestBody Post post, HttpServletResponse res) {
		Post p = postServ.replace(pid, post);
		if (p != null) {
			res.setStatus(201);
		} else {
			res.setStatus(500);
		}
		return p;
	}

	@RequestMapping(path = "posts/{pid}", method = RequestMethod.DELETE)
	public boolean deletePost(@PathVariable Integer pid, HttpServletResponse res) {
		boolean deleted = postServ.delete(pid);
		if (deleted == true) {
			res.setStatus(201);
		} else {
			res.setStatus(500);
		}
		return deleted;
	}

}
