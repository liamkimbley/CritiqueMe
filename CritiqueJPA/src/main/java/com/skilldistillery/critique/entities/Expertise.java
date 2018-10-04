
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

	/* Fields */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "profile_expertise", 
	joinColumns = @JoinColumn(name = "expertise_id"), 
	inverseJoinColumns = @JoinColumn(name = "profile_id"))
	private List<Profile> profiles;

	/* Fields */

	/* Constructors */

	public Expertise() {
	}

	public Expertise(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	/* Constructors */

	/* Getters and Setters */

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

	/* Getters and Setters */

	/* Helpers */

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((profiles == null) ? 0 : profiles.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Expertise other = (Expertise) obj;
		if (id != other.id)
			return false;
		if (profiles == null) {
			if (other.profiles != null)
				return false;
		} else if (!profiles.equals(other.profiles))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/* Helpers */

	@Override
	public String toString() {
		return "Expertise [id=" + id + ", title=" + title + "]";
	}
}
