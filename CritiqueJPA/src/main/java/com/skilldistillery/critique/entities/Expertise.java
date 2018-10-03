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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Expertise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="profile_expertise",
	joinColumns = @JoinColumn(name="expertise_id"),
	inverseJoinColumns = @JoinColumn(name="profile_id"))
	private List<Profile> profiles;

	public Expertise() {
	}

	public Expertise(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfile(Profile profile) {
		if (profiles == null) {
			profiles = new ArrayList<>();
		}
		if (!profiles.contains(profile)) {
			profiles.add(profile);
			profile.addExpertise(this);
		}
	}

	public void removeProfile(Profile profile) {
		if (profiles != null && profiles.contains(profile)) {
			profiles.remove(profile);
			profile.removeExpertise(this);
		}
	}

	@Override
	public String toString() {
		return "Expertise [id=" + id + ", title=" + title + "]";
	}
}
