package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		emf = Persistence.createEntityManagerFactory("CritiqueJPA");
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
		assertEquals("no", com.getContent());
	}
	
	@Test
	void test_comment_has_one_post() {
		assertEquals("buy my album", com.getPost().getContent());
	}
	
	@Test
	void test_comment_has_one_profile() {
		assertEquals("Jeff", com.getProfile().getFirstName());
	}
	
//	@Test
//	void test_one_comment_can_have_many_votes() {
//	}

}
