package com.skilldistillery.critique.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilldistillery.critique.entities.Expertise;
import com.skilldistillery.critique.entities.Location;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.User;
import com.skilldistillery.critique.repositories.ProfileRepository;

@Repository
@Transactional // (noRollbackFor = Exception.class)
@Service
public class AuthServiceImpl implements AuthService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ProfileRepository profRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private LocationService locServ;

	@Override
	public User register(String json) {
		ObjectMapper om = new ObjectMapper();
		User user = null;
		Profile defaultProfile = new Profile();

		try {
			user = om.readValue(json, User.class);

			String encodedPW = encoder.encode(user.getPassword());
			user.setPassword(encodedPW); // only persist encoded password

			// set other fields to default values
			user.setActive(true);
			user.setRole("standard");
			defaultProfile.setFirstName("");
			defaultProfile.setLastName("");
			defaultProfile.setUser(user);
			defaultProfile
					.setImageUrl("https://cdn2.vectorstock.com/i/1000x1000/20/76/question-mark-vector-972076.jpg");
			defaultProfile.setBio("");
			Location defaultLocation = new Location();
			defaultLocation.setCity("Somewhere");
			defaultLocation.setState("Overtherainbow");
			defaultLocation.setCountry("Oz");
			defaultProfile.setLocation(locServ.create(defaultLocation));
			defaultProfile.addExpertise(em.find(Expertise.class, 1));
			profRepo.saveAndFlush(defaultProfile);
		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}

}
