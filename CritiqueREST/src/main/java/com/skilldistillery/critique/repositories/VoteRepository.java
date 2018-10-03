package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.critique.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

}
