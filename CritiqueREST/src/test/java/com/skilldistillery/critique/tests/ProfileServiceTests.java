package com.skilldistillery.critique.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.services.ProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceTests {
	@Autowired
	private ProfileService profService;
	
	@Test
	public void test_get_one_profile_by_id() {
		Profile prof = profService.findProfileById(1);
		assertEquals("test", prof.getFirstName());
	}
	
	@Test
	public void test_get_expertise_list_for_profile() {
		Profile prof = profService.queryByUsernameWithUser("mjones");
		assertNotNull(prof.getSkills());
		assertNotEquals(0, prof.getSkills().size());
		assertEquals(5, prof.getSkills().get(0).getId());
		assertEquals("Music", prof.getSkills().get(0).getTitle());
	}

}
