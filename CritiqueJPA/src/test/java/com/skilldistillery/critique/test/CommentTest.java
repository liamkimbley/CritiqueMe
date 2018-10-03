package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Comment;

class CommentTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private Comment com;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueMe");
		em = emf.createEntityManager();
		com = em.find(Comment.class, 1);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_comment() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_many_comments_can_be_in_one_post() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_many_comments_can_come_from_one_profile() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_comment_can_have_many_votes() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_comment_can_only_have_one_vote_per_profile() {
		fail("Not yet implemented");
	}

}
