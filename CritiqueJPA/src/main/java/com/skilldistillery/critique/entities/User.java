package com.skilldistillery.critique.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String username;
	private String password;
	private String role;
	private Boolean active;
	@ManyToMany
	@JoinTable(name = "friend", 
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private List<User> friends;

	public User() {
	}

	public User(int id, String email, String username, String password, String role, Boolean active) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public void addFriend(User friend) {
		if (friends == null)
			friends = new ArrayList<>();

		if (!friends.contains(friend)) {
			friends.add(friend);
			friend.getFriends().add(this);
		}

	}

	public void removeFriend(User friend) {
		if (friends != null && friends.contains(friend)) {
			friends.remove(friend);
			friend.getFriends().remove(this);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", role=" + role + ", active="
				+ active + "]";
	}

}
