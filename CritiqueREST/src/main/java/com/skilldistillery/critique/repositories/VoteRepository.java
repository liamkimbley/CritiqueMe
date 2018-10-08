package com.skilldistillery.critique.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.critique.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

	@Query("SELECT v FROM Vote v WHERE v.id.comment.id = :cid AND v.id.profile.id = :pid")
	public Vote findByCommentIdAndProfileId(@Param ("cid") Integer cid, @Param ("pid") Integer pid);

	@Query("SELECT v FROM Vote v WHERE v.id.comment.id = :cid")
	public List<Vote> findByCommentId(@Param ("cid") Integer cid);
	
	@Modifying
	@Query("DELETE FROM Vote v WHERE v.id.comment.id = :cid")
	public void deleteVotesForCommentsByCommentId(@Param ("cid") Integer cid);
}
