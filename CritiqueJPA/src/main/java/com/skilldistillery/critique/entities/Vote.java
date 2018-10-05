package com.skilldistillery.critique.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Vote {

	@EmbeddedId
	private VoteKey id; // key represents comment and profile combined

	private Boolean vote;

	public VoteKey getId() {
		return id;
	}

	public void setId(VoteKey id) {
		this.id = id;
	}

	public Boolean getVote() {
		return vote;
	}

	public void setVote(Boolean vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", vote=" + vote + "]";
	}

}
