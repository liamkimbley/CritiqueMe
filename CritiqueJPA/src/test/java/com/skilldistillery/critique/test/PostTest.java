package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Post;

class PostTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private Post post;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueMe");
		em = emf.createEntityManager();
		post = em.find(Post.class, 1);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_post() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_many_posts_to_one_profile() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_post_to_many_comments() {
		fail("Not yet implemented");
	}
	
	@Test
	void test_one_post_to_many_categories() {
		fail("Not yet implemented");
	}
}
