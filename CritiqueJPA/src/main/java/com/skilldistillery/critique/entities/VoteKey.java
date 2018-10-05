package com.skilldistillery.critique.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class VoteKey implements Serializable {

	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public VoteKey() {
	}

	public VoteKey(Comment comment, Profile profile) {
		super();
		this.comment = comment;
		this.profile = profile;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "VoteKey [comment=" + comment + ", profile=" + profile + "]";
	}

}
