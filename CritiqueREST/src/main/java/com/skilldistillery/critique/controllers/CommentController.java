package com.skilldistillery.critique.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Comment;
import com.skilldistillery.critique.services.CommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4201" })
public class CommentController {

	@Autowired
	private CommentService comServ;

	@RequestMapping(path = "comments/{cid}", method = RequestMethod.GET)
	public Comment getSingleComment(@PathVariable Integer cid, HttpServletRequest req, HttpServletResponse res) {
		Comment comment = comServ.findByCommentId(cid);
		if (comment != null) {
			res.setStatus(200);
			return comment;
		} else {
			res.setStatus(500);
			return null;
		}
	}

//	************************************* hard coded profile id into comments (same as post)
	@RequestMapping(path = "posts/{pid}/comments", method = RequestMethod.POST)
	public Comment createComment(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer pid,
			@RequestBody Comment com, Principal principal) {
		Comment comment = comServ.createNewCommentOnPost(pid, com, principal.getName());
		if (comment != null) {
			res.setStatus(201);
			return comment;
		} else {
			res.setStatus(500);
			return null;
		}
	}

	@RequestMapping(path="comments/{cid}", method=RequestMethod.PUT)
	public Comment updateComment(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer cid, @RequestBody Comment com) {
		Comment c = comServ.update(cid, com);
		if (c == null) {
			res.setStatus(404);
		}
		if (c != null) {
			res.setStatus(200);
		}
		return c;
	}

	@RequestMapping(path="comments/{cid}", method=RequestMethod.DELETE)
	public Boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer cid) {
		Boolean deleted = comServ.deleteCommentById(cid);
		if (deleted == true) {
			res.setStatus(204);
			return true;
		}
		else {
			res.setStatus(400);
			return false;
		}
	}
}
