package com.skilldistillery.critique.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.critique.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByProfile_Id(Integer id);
	public List<Post> findByCreatedDate(LocalDate date);
	@Query("SELECT p FROM Post p JOIN FETCH p.comments WHERE p.id = :id")
	public Post queryForPostWithCommentsByPostId(Integer id);
	
	@Query("SELECT p FROM Post p, Category c WHERE  c.id = :cid")
	public List<Post> queryForPostsByCategoryId(@Param ("cid") Integer cid);
}
