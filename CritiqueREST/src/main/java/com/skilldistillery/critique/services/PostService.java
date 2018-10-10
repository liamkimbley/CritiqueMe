package com.skilldistillery.critique.services;

import java.time.LocalDate;
import java.util.List;

import com.skilldistillery.critique.entities.Post;

public interface PostService {

	public List<Post> findAllPosts();

	public List<Post> findPostsByCategoryId(Integer id);

	public List<Post> findByTitle(String title);

	public Post findPostById(Integer id);

	public List<Post> findPostsByProfileId(String username, Integer pid);

	public Post create(String username, Post post, Integer id);

	public Post update(String username, Integer id, Post post);

	public Post replace(String username, Integer id, Post post);

	public boolean delete(String username, Integer id);

	public List<Post> findPostsByCreateDate(LocalDate date);
}
