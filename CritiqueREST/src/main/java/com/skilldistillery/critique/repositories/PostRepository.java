package com.skilldistillery.critique.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByProfile_Id(Integer id);
	public List<Post> findByCreatedDate(LocalDate date);
}
