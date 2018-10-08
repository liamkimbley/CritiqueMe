package com.skilldistillery.critique.services;

import java.util.List;

import com.skilldistillery.critique.entities.Vote;

public interface VoteService {
	
	public List<Vote> findAllVotesByCommentId(Integer id);
	public Vote findOneVoteOnComment(Integer profId, Integer comId);
	public Vote createVote(Vote vote, Integer cid, String username);
	public Vote updateVote(Vote vote, Integer vid, Integer cid);
	public Boolean deleteAllVotesForComment(Integer cid);
}
