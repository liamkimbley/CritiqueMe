package com.skilldistillery.critique.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
	private Profile prof2;
	private Profile prof3;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueJPA");
		em = emf.createEntityManager();
		prof = em.find(Profile.class, 1);
		prof2 = em.find(Profile.class, 2);
		prof3 = em.find(Profile.class, 3);

	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_profile() {
		assertEquals("test", prof.getFirstName());
		assertEquals("https://cdn2.vectorstock.com/i/1000x1000/20/76/question-mark-vector-972076.jpg", prof.getImageUrl());
	}
	
	@Test
	void test_one_profile_to_one_user() {
		assertEquals("test", prof.getUser().getUsername());
		assertEquals("test@sd.com", prof.getUser().getEmail());
	}
	
	@Test
	void test_profile_has_a_location() {
		assertEquals("Denver", prof.getLocation().getCity());
	}
	
//	@Test
//	void test_one_profile_can_have_many_votes() {
//		
//	}
	
//	@Test
//	void test_one_profile_can_only_vote_once_per_comment() {
//	}
//	
	@Test
	void test_one_profile_can_have_many_expertises() {
		assertNotEquals(0, prof2.getSkills().size());
		assertEquals("Music", prof2.getSkills().get(0).getTitle());
	}
	
	@Test
	void test_one_profile_can_have_many_posts() {
		assertNotEquals(0, prof2.getPosts().size());
	}
	
	@Test
	void test_one_profile_can_have_many_comments() {
		assertNotEquals(0, prof3.getComments().size());
	}

}
