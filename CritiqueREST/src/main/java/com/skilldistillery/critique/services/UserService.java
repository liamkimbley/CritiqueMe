package com.skilldistillery.critique.services;

import java.util.List;

import com.skilldistillery.critique.entities.User;

public interface UserService {
	
	public List<User> index();

	public User findOneUserByUsername(String name);

	public User findByEmail(String email);

	public List<User> findByActive(Boolean active);

	public User show(Integer id);
}
