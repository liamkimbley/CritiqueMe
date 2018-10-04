package com.skilldistillery.critique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Comment;
import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.repositories.CommentRepository;
import com.skilldistillery.critique.repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository comRepo;
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Comment> findCommentsByPost(int id) {
		return comRepo.findByPost_Id(id);
		
	}

	@Override
	public Comment createNewCommentOnPost(int id, Comment comment) {
		Comment com = new Comment();
		Post post = postRepo.findById(id).get();
		if (post != null) {
			com.setPost(post);
		}
		com.setContent(comment.getContent());
		
		return comRepo.saveAndFlush(com);
		
	}

	@Override
	public boolean deleteCommentById(int cid) {
		try {
			comRepo.deleteById(cid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
