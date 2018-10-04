package com.skilldistillery.critique.services;

import java.util.List;
import java.util.Optional;

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
	public List<Comment> findCommentsByPost(Integer id) {
		return comRepo.findByPost_Id(id);
		
	}

	@Override
	public Comment createNewCommentOnPost(Integer id, Comment comment) {
		Comment com = new Comment();
		Post post = postRepo.findById(id).get();
		if (post != null) {
			com.setPost(post);
		}
		com.setContent(comment.getContent());
		
		return comRepo.saveAndFlush(com);
		
	}

	@Override
	public boolean deleteCommentById(Integer cid) {
		try {
			comRepo.deleteById(cid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Comment findByCommentId(Integer id) {
		Optional<Comment> c = comRepo.findById(id);
		
		if (c.isPresent()) {
			return c.get();
		}
		return null;
	}

	@Override
	public Comment update(Integer id, Comment com) {
		Optional<Comment> oc = comRepo.findById(id);
		
		if (oc.isPresent()) {
			Comment c = oc.get();
			if (com.getContent() != null && !com.getContent().equals("")) {
				c.setContent(com.getContent());
			}
			return comRepo.saveAndFlush(c);
		}
		return null;
		
	}
	
}
