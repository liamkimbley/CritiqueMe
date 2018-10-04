package com.skilldistillery.critique.services;

import java.time.LocalDate;
import java.util.List;

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
		return null;
		
	}

	@Override
	public List<Post> findPostsByCreateDate(LocalDate date) {
		return null;
		
	}

	@Override
	public List<Post> findAllPosts() {
		return null;
		
	}

	@Override
	public Post findPostById(int id) {
		return null;
		
	}

	@Override
	public Post create(Post post) {
		return null;
		
	}

	@Override
	public Post update(int id, Post post) {
		return null;
		
	}

	@Override
	public Post replace(int id, Post post) {
		return null;
		
	}

	@Override
	public boolean delete(int id) {
		return false;
		
	}

}
