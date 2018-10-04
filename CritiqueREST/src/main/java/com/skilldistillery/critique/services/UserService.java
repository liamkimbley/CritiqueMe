package com.skilldistillery.critique.services;

import java.util.List;

import com.skilldistillery.critique.entities.User;

public interface UserService {
	public User findByUsername(String name);
	public User findByEmail(String email);
	public User findOneUserByUsername(String name);
	public List<User> findByActive(Boolean active);
	public List<User> findByRole(String role);
}
