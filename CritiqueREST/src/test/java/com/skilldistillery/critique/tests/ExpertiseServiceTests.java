package com.skilldistillery.critique.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilldistillery.critique.entities.Expertise;
import com.skilldistillery.critique.services.ExpertiseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpertiseServiceTests {
	@Autowired
	private ExpertiseService skillService;
	
	@Test
	public void test_all_skills() {
		List<Expertise> skills = skillService.index();
		assertNotNull(skills);
		assertNotEquals(0, skills.size());
	}
	
}
