package com.skilldistillery.critique.services;

import java.util.List;

import com.skilldistillery.critique.entities.Comment;

public interface CommentService {
	
	public List<Comment> findCommentsByPost(Integer id);
	public Comment createNewCommentOnPost(Integer id, Comment comment, String username);
	public Boolean deleteCommentById(Integer cid);
	public Comment findByCommentId(Integer id);
	public Comment update(Integer id, Comment com);
	
}
