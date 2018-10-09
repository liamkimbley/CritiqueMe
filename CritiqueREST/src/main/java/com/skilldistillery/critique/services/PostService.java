package com.skilldistillery.critique.services;

import java.time.LocalDate;
import java.util.List;

import com.skilldistillery.critique.entities.Post;

public interface PostService {

	public List<Post> findAllPosts();

	public List<Post> findPostsByCategoryId(Integer id);

	public List<Post> findByTitle(String title);

	public Post findPostById(Integer id);

	public List<Post> findPostsByProfileId(Integer pid);

	public Post create(Post post, Integer id);

	public Post update(Integer id, Post post);

	public Post replace(Integer id, Post post);

	public boolean delete(Integer id);

	
	// Queries not passing tests
	public List<Post> findPostsByCreateDate(LocalDate date);
}
