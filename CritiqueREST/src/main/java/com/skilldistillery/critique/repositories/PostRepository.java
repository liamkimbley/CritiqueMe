package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
