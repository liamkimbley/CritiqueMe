package com.skilldistillery.critique.test;

import static org.junit.Assert.assertEquals;
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
		emf = Persistence.createEntityManagerFactory("CritiqueJPA");
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
		assertEquals("buy my album", post.getContent());
	}
	
	@Test
	void test_many_posts_to_one_profile() {
		assertEquals("Mike", post.getProfile().getFirstName());
	}
	
	@Test
	void test_one_post_to_many_comments() {
		assertEquals("no", post.getComments().get(0).getContent());
	}
	
	@Test
	void test_one_post_to_many_categories() {
		assertEquals("Music", post.getCategories().get(0).getName());
	}
}
