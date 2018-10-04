package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.User;

class UserTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private User user;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueJPA");
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_user() {
		assertEquals("test", user.getUsername());
	}
	
	@Test
	void test_one_user_can_have_many_friends() {
		user = em.find(User.class, 2);
		assertEquals("myusernameisjeff", user.getFriends().get(0).getUsername());
	}

}
