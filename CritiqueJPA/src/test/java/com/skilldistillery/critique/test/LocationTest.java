package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Location;

class LocationTest {
	private EntityManager em;
	private EntityManagerFactory emf;
	private Location loc;
	
	@BeforeEach
	public void setup() {
		emf = Persistence.createEntityManagerFactory("CritiqueMe");
		em = emf.createEntityManager();
		loc = em.find(Location.class, 1);
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
		emf.close();
	}
	
	@Test
	void test_location() {
		fail("Not yet implemented");
	}

	@Test
	void test_one_location_has_many_profiles() {
		fail("Not yet implemented");
	}
}
