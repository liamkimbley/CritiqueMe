package com.skilldistillery.critique.services;

import java.time.LocalDate;
import java.util.List;

import com.skilldistillery.critique.entities.Post;

public interface PostService {

	public List<Post> findPostsByCategoryId(int id);
	public List<Post> findPostsByCreateDate(LocalDate date);
	public List<Post> findAllPosts();
	public Post findPostById(int id);
	public Post create(Post post);
	public Post update(int id, Post post);
	public Post replace(int id, Post post);
	public boolean delete(int id);
}
