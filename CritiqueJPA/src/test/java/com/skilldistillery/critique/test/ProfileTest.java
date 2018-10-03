package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Profile;

class ProfileTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private Profile prof;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueMe");
		em = emf.createEntityManager();
		prof = em.find(Profile.class, 1);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_profile() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_profile_to_one_user() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_many_profiles_to_one_location() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_profile_can_have_many_votes() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_profile_can_only_vote_once_per_comment() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_profile_can_have_many_expertises() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_profile_can_have_many_posts() {
		fail("Not yet implemented");
	}

}
