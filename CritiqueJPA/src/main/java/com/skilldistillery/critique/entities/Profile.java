package com.skilldistillery.critique.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String bio;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "profile")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "profile")
	private List<Post> posts;

	@ManyToMany(mappedBy = "profiles")
	private List<Expertise> skills;

	public Profile() {
	}

	public Profile(int id, String firstName, String lastName, String bio, Location location, User user) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
		this.location = location;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Expertise> getSkills() {
		return skills;
	}

	public void setSkills(List<Expertise> professions) {
		this.skills = professions;
	}

	public void addComment(Comment comment) {
		if (this.comments == null) {
			comments = new ArrayList<>();
		}
		if (!comments.contains(comment)) {
			comments.add(comment);
			if (comment.getProfile() != null) {
				comment.getProfile().getComments().remove(comment);
			}
			comment.setProfile(this);
		}
	}

	public void removeComment(Comment comment) {
		comment.setProfile(null);
		if (this.comments != null) {
			comments.remove(comment);
		}
	}
	
	public void addExpertise(Expertise skill) {
		if (this.skills == null) {
			skills = new ArrayList<>();
		}
		if (!skills.contains(skill)) {
			skills.add(skill);
			skill.addProfile(this);
		}
	}
	
	public void removeExpertise(Expertise skill) {
		skill.setProfiles(null);
		if (this.skills != null) {
			skills.remove(skill);
		}
	}
	
	public void addPost(Post post) {
		if (this.posts == null) {
			posts = new ArrayList<>();
		}
		if (!posts.contains(post)) {
			posts.add(post);
			if (post.getProfile() != null) {
				post.getProfile().getPosts().remove(post);
			}
			post.setProfile(this);
		}
	}
	
	public void removePost(Post post) {
		post.setProfile(null);
		if (this.posts != null) {
			posts.remove(post);
		}
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + "]";
	}
}
