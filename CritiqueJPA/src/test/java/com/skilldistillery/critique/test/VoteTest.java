package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Vote;

class VoteTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private Vote vote;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueMe");
		em = emf.createEntityManager();
		vote = em.find(Vote.class, 1);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_many_votes_to_one_profile() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_many_votes_to_one_comment() {
		fail("Not yet implemented");
	}

}
