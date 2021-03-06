package com.skilldistillery.critique.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilldistillery.critique.entities.Expertise;
import com.skilldistillery.critique.entities.Location;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.services.ExpertiseService;
import com.skilldistillery.critique.services.ProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceTests {
	@Autowired
	private ProfileService profService;
	
	@Autowired
	private ExpertiseService skillService;
	
	@Test
	public void test_get_one_profile_by_id() {
		Profile prof = profService.findProfileById(1);
		assertEquals("test", prof.getFirstName());
	}
	
	@Test // This will fail until we fix the null skills issue and can use the join fetch query
	public void test_get_expertise_list_for_profile() {
		Profile prof = profService.queryByUsernameWithUser("test");
		assertNotNull(prof.getSkills());
		assertNotEquals(0, prof.getSkills().size());
		assertEquals(1, prof.getSkills().get(0).getId());
		assertEquals("None", prof.getSkills().get(0).getTitle());
	}
	
	
	@Test
	public void test_update_updates_location_and_skills() {
		Profile prof = profService.queryByUsernameWithUser("test");
		List<Expertise> skills = prof.getSkills();
		Expertise e = skillService.findOneById(17);
		skills.add(e);
		prof.setSkills(skills);
		Location l = new Location();
		l.setCity("Windhelm");
		l.setState("Skyrim");
		l.setCountry("Morrowind");
		prof.setLocation(l);
		prof.setBio("I was an adventurer once... Then I took an arrow to the knee.");
		prof.setFirstName("test");
		Profile updatedProf = profService.update(1, prof);
		assertEquals("I was an adventurer once... Then I took an arrow to the knee.", updatedProf.getBio());
		assertNotEquals(0, updatedProf.getSkills().size());
		assertEquals("Windhelm", updatedProf.getLocation().getCity());
		assertTrue(prof.getSkills().contains(e));
	}

}
