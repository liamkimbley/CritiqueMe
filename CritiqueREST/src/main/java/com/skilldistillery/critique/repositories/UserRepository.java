package com.skilldistillery.critique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findOneUserByUsername(String name);
	public User findByEmail(String email);
	public List<User> findByActive(Boolean active);
}
