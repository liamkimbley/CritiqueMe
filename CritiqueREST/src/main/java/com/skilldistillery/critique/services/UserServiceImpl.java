package com.skilldistillery.critique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.User;
import com.skilldistillery.critique.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User findByUsername(String name) {
		return userRepo.findByUsername(name);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User findOneUserByUsername(String name) {
		return userRepo.findOneUserByUsername(name);
	}

	@Override
	public List<User> findByActive(Boolean active) {
		return userRepo.findByActive(active);
	}

	@Override
	public List<User> findByRole(String role) {
		return userRepo.findByRole(role);
	}

}
