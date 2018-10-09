package com.skilldistillery.critique.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Comment;
import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.repositories.CommentRepository;
import com.skilldistillery.critique.repositories.PostRepository;
import com.skilldistillery.critique.repositories.ProfileRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository comRepo;
	@Autowired
	private PostRepository postRepo;

	@Autowired
	private ProfileRepository profRepo;

	@Override
	public List<Comment> findCommentsByPost(Integer id) {
		List<Comment> comments = comRepo.findByPostId(id);
		if (comments.isEmpty()) {
			return null;
		}
		return comments;

	}

	@Override
	public Comment createNewCommentOnPost(Integer id, Comment comment, Integer profId) {
		Comment com = new Comment();
		if (comment.getContent() != null && !comment.getContent().equals("")) {
			com.setContent(comment.getContent());
		}
		if (comment.getPost() != null) {
			com.setPost(comment.getPost());
		}
		if (comment.getPost() == null) {
			Optional<Post> op = postRepo.findById(id);
			if (op.isPresent()) {
				com.setPost(op.get());
			}
		}
		if (comment.getProfile() == null) {
			Optional<Profile> pr = profRepo.findById(profId);
			if (pr.isPresent()) {
				com.setProfile(pr.get());
			}
		}
		if (comment.getProfile() != null) {
			com.setProfile(comment.getProfile());
		}
		return comRepo.saveAndFlush(com);
	}

	@Override
	public Boolean deleteCommentById(Integer cid) {
		Comment toDelete = comRepo.findById(cid).get();
		if (toDelete != null) {
			try {
				comRepo.deleteById(cid);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
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
