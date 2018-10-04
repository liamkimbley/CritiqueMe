package com.skilldistillery.critique.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.User;
import com.skilldistillery.critique.repositories.UserRepository;

@Repository
@Transactional
@Service
public class AuthServiceImpl implements AuthService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository userRepo;

//	@Autowired
//	private PasswordEncoder encoder;

	@Override
	public User register(String json) {
		ObjectMapper om = new ObjectMapper();
		User user = null;
		Profile defaultProfile = new Profile();

		try {
			user = om.readValue(json, User.class);

//			String encodedPW = encoder.encode(user.getPassword());
//			user.setPassword(encodedPW); // only persist encoded password

			// set other fields to default values
			user.setActive(true);
			user.setRole("standard");
			defaultProfile.setFirstName("");
			defaultProfile.setLastName("");
			defaultProfile.setUser(user);

			userRepo.saveAndFlush(user);
		} catch (Exception e) {
			System.out.println(e);
		}		
		return user;
	}

}

