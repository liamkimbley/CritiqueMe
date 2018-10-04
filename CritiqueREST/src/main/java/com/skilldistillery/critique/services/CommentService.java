package com.skilldistillery.critique.services;

import java.util.List;

import com.skilldistillery.critique.entities.Comment;

public interface CommentService {
	
	public List<Comment> findCommentsByPost(int id);
	public Comment createNewCommentOnPost(int id, Comment comment);
	public boolean deleteCommentById(int cid);

}
