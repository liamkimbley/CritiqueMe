package com.skilldistillery.critique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	public List<Comment> findByPostId(Integer id);
	public List<Comment> findByProfileId(Integer id);

}
