package com.skilldistillery.critique.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.services.PostService;
import com.skilldistillery.critique.services.ProfileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTests {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ProfileService profService;
	
	@Test
	public void test_findAllPosts() {
		Set<Post> posts = postService.findAllPosts();
		assertNotNull(posts);
		assertNotEquals(0, posts.size());
	}
	
	@Test
	public void test_findPostsByCategoryId() {
		List<Post> posts = postService.findPostsByCategoryId(1);
		assertNotNull(posts);
		assertNotEquals(0, posts.size());
	}
	
	@Test
	public void test_findByTitleContaining() {
		List<Post> posts = postService.findByTitle("yo");
		assertNotEquals(0, posts.size());
	}
	
	@Test
	public void test_findPostById() {
		Post post = postService.findPostById(1);
		assertEquals("yo", post.getTitle());
	}
	
	@Test
	public void test_findPostsByProfileId() {
		List<Post> posts = postService.findPostsByProfileId(2);
		assertNotNull(posts);
		assertNotEquals(0, posts.size());
	}
	
	@Test
	public void test_create() {
		Post post = new Post();
		Profile prof = profService.findProfileById(1);
		post.setContent("test content");
		post.setProfile(prof);
		post.setTitle("Test Title");
		Post createdPost = postService.create(post, 1);
		assertNotNull(createdPost);
	}
	
	@Test
	public void test_replace() {
		Post toUpdate = postService.findPostById(1);
		toUpdate.setTitle("yooooo");
		toUpdate.setProfile(profService.findProfileById(2));
		Post updatedPost = postService.replace(1, toUpdate);
		assertEquals("yooooo", updatedPost.getTitle());
	}
	
	@Test
	public void test_update() {
		Post toUpdate = postService.findPostById(1);
		toUpdate.setTitle("yo");
		toUpdate.setProfile(profService.findProfileById(2));
		Post updatedPost = postService.update(1, toUpdate);
		assertEquals("yo", updatedPost.getTitle());
	}
	
	@Test
	public void test_delete() {
//		assertTrue(postService.delete(5));
	}
	
	@Test
	public void test_findPostsByCreateDate() {
		Post post = postService.findPostById(1);
		LocalDate date = post.getCreatedDate();
		List<Post> posts = postService.findPostsByCreateDate(date);
		assertNotNull(posts);
		assertNotEquals(0, posts.size());
	}
}
