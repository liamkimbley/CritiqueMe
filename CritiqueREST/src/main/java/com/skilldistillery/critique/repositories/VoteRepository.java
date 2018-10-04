package com.skilldistillery.critique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.critique.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

//	@Query("SELECT v FROM Vote v WHERE v.comment.id = :cid AND v.profile.id = :pid")
//	public Vote findByCommentIdAndProfileId(@Param ("cid") Integer cid, @Param ("pid") Integer pid);
}
