package com.skilldistillery.critique.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
	public Set<Post> index(HttpServletRequest req, HttpServletResponse res) {
		Set<Post> posts = postServ.findAllPosts();
		if (posts != null) {
			res.setStatus(200);
			return posts;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path = "categories/{id}/posts", method = RequestMethod.GET)
	public List<Post> postByCategory(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse res) {
		List<Post> posts = postServ.findPostsByCategoryId(id);
		if (posts != null) {
			res.setStatus(200);
			return posts;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path = "posts/{pid}", method = RequestMethod.GET)
	public Post getSinglePost(@PathVariable Integer pid, HttpServletRequest req, HttpServletResponse res) {
		Post p = postServ.findPostById(pid);
		if (p != null) {
			res.setStatus(200);
			return p;
		} else {
			res.setStatus(500);
			return null;
		}
	}
	
	@RequestMapping(path = "posts/title/{title}", method = RequestMethod.GET)
	public List<Post> getPostByTitle(@PathVariable String title, HttpServletRequest req, HttpServletResponse res) {
		List<Post> posts = postServ.findByTitle(title);
		if (posts != null) {
			res.setStatus(200);
			return posts;
		} else {
			res.setStatus(500);
			return null;
		}
	}

//	***************************************** hard coded profile id into post (same as comments)
	@RequestMapping(path = "posts", method = RequestMethod.POST)
	public Post create(@RequestBody Post post, HttpServletRequest req, HttpServletResponse res) {
		Post p = postServ.create(post, 1);
		if (p != null) {
			res.setStatus(201);
		} else {
			res.setStatus(500);
		}
		return p;
	}

	@RequestMapping(path = "posts/{pid}", method = RequestMethod.PUT)
	public Post replacePost(@PathVariable Integer pid, @RequestBody Post post, HttpServletRequest req, HttpServletResponse res) {
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
