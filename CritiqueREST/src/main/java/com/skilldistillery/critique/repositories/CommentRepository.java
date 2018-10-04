package com.skilldistillery.critique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	public List<Comment> findByPost_Id(Integer id);
	public List<Comment> findByProfile_Id(Integer id);

}
