package com.skilldistillery.critique.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Comment;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.Vote;
import com.skilldistillery.critique.entities.VoteKey;

class VoteTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private Vote vote;
	
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueJPA");
		em = emf.createEntityManager();
		Comment com = em.find(Comment.class, 1);
		Profile prof = em.find(Profile.class, 1);
		VoteKey vk = new VoteKey(com, prof);
		vote = em.find(Vote.class, vk);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_vote_vote() {
		assertFalse(vote.getVote());
	}

}
