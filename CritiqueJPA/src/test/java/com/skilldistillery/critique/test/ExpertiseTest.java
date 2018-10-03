package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Expertise;

class ExpertiseTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private Expertise exp;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueMe");
		em = emf.createEntityManager();
		exp = em.find(Expertise.class, 1);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_one_expertise_can_be_in_many_profiles() {
		fail("Not yet implemented");
	}

}
