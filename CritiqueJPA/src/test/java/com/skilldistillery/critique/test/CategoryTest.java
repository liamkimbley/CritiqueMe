package com.skilldistillery.critique.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.critique.entities.Category;

class CategoryTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Category cat;
	
	@BeforeAll
	public static void beforeTests() throws Exception {
		emf = Persistence.createEntityManagerFactory("CritiqueJPA");
	}
	
	@BeforeEach
	public void setup () {
		em = emf.createEntityManager();
		cat = em.find(Category.class, 1);
	}

	@AfterAll
	public static void afterTests() {
		emf.close();
	}
	
	@AfterEach
	public void tearDown() {
		em.close();
	}
	
	@Test
	void test_category_has_name() {
		assertEquals("Music", cat.getName());
	}

}
