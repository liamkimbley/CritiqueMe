package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findByName(String name);
}
