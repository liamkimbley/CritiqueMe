package com.skilldistillery.critique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Post> findPostsByCategoryId(int id) {
		return postRepo.queryForPostsByCategoryId(id);

	}

	@Override
	public List<Post> findPostsByCreateDate(LocalDate date) {
		return postRepo.findByCreatedDate(date);

	}

	@Override
	public List<Post> findAllPosts() {
		return postRepo.findAll();

	}

	@Override
	public Post findPostById(int id) {
		Post p = postRepo.queryForPostWithCommentsByPostId(id);

		if (p != null) {
			return p;
		}

		return null;
	}

	@Override
	public Post create(Post post) {
		Post p = new Post();

		if (post != null) {
			if (post.getContent() != null && !post.getContent().equals("")) {
				p.setContent(post.getContent());
			}
			if (post.getProfile() != null) {
				p.setProfile(post.getProfile());
			}
		}

		return postRepo.saveAndFlush(p);

	}

	@Override
	public Post update(int id, Post post) {
		Optional<Post> op = postRepo.findById(id);

		if (op.isPresent()) {
			Post p = op.get();
			if (post.getContent() != null && !post.getContent().equals("")) {
				p.setContent(post.getContent());
			}
			if (post.getProfile() != null) {
				p.setProfile(post.getProfile());
			}
			return postRepo.saveAndFlush(p);
		}
		return null;

	}

	@Override
	public Post replace(int id, Post post) {
		Optional<Post> op = postRepo.findById(id);

		if (op.isPresent()) {
			Post p = op.get();
			if (post.getContent() != null && !post.getContent().equals("")) {
				p.setContent(post.getContent());
			}
			if (post.getProfile() != null) {
				p.setProfile(post.getProfile());
			}
			return postRepo.saveAndFlush(p);
		}
		return null;

	}

	@Override
	public boolean delete(int id) {
		try {
			postRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
