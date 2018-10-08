package com.skilldistillery.critique.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Profile {

	/* Fields */

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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "image")
	private String imageUrl;

	@JsonIgnore
	@OneToMany(mappedBy = "profile")
	private List<Comment> comments;

	@JsonIgnore
	@OneToMany(mappedBy = "profile", cascade = CascadeType.PERSIST)
	private List<Post> posts;

	@ManyToMany(mappedBy = "profiles")
	private List<Expertise> skills;

	/* Fields */

	/* Constructors */

	public Profile() {
	}

	public Profile(int id, String firstName, String lastName, String bio, Location location, User user, String imageUrl) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
		this.location = location;
		this.user = user;
		this.imageUrl = imageUrl;
	}

	/* Constructors */

	/* Getters and Setters */

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
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	/* Getters and Setters */

	/* Helpers */

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + "]";
	}
}
