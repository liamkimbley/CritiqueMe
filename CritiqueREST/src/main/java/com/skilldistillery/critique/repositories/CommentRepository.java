package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
