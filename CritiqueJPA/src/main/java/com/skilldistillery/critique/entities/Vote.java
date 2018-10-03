package com.skilldistillery.critique.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDate createdDate;

	@JoinColumn(name = "profile_id")
	private Profile profile;

	@JoinColumn(name = "comment_id")
	private Comment comment;

	public Vote() {
	}

	public Vote(int id, LocalDate createdDate, Profile profile, Comment comment) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.profile = profile;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", createdDate=" + createdDate + "]";
	}

}
