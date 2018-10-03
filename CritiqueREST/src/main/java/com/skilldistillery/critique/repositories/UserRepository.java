package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
//	public User findByUsername(String name);
//	public User findByEmail(String email);
//	public User findOneUserByUsername(String name);

}
