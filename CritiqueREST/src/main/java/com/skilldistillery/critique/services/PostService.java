package com.skilldistillery.critique.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.skilldistillery.critique.entities.Post;

public interface PostService {

	public Set<Post> findAllPosts();

	public List<Post> findPostsByCategoryId(Integer id);

	public List<Post> findByTitle(String title);

	public Post findPostById(Integer id);

	public List<Post> findPostsByProfileId(Integer pid);

	public Post create(Post post, String username);

	public Post update(Integer id, Post post);

	public Post replace(Integer id, Post post);

	public boolean delete(Integer id);

	public List<Post> findPostsByCreateDate(LocalDate date);
}
