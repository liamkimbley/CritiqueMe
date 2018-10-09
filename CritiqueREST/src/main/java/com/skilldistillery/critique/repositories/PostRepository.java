package com.skilldistillery.critique.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.critique.entities.Category;
import com.skilldistillery.critique.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByProfile_Id(Integer id);
	public List<Post> findByCreatedDate(LocalDate date);
	public List<Post> findByTitleContaining(String title);
	@Query("SELECT p FROM Post p JOIN FETCH p.comments WHERE p.id = :id")
	public Post queryForPostWithCommentsByPostId(@Param ("id") Integer id);
	
	@Query("SELECT p FROM Post p WHERE :cat MEMBER OF p.categories")
	public List<Post> queryForPostsByCategory(@Param ("cat") Category cat);
	
	@Query("SELECT p FROM Post p JOIN FETCH p.categories")
	public List<Post> queryForPostsWithCategories();
	
	@Query("SELECT p FROM Post p JOIN FETCH p.comments WHERE p.profile.id = :pid")
	public List<Post> queryForPostWithCommentsByProfileId(@Param ("pid") Integer pid);
}
