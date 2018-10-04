package com.skilldistillery.critique.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.User;
import com.skilldistillery.critique.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> index() {
		List<User> users = userRepo.findAll();
		if (users.isEmpty()) {
			return null;
		}
		return users;
	}

	@Override
	public User show(Integer id) {
		Optional<User> uOptional = userRepo.findById(id);
		if (uOptional.isPresent()) {
			User user = uOptional.get();
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User findOneUserByUsername(String name) {
		return userRepo.findOneUserByUsername(name);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> findByActive(Boolean active) {
		// revisit later
		active = true;
		return userRepo.findByActive(active);
	}

}
