package com.skilldistillery.critique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Comment;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.Vote;
import com.skilldistillery.critique.entities.VoteKey;
import com.skilldistillery.critique.repositories.CommentRepository;
import com.skilldistillery.critique.repositories.ProfileRepository;
import com.skilldistillery.critique.repositories.VoteRepository;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private VoteRepository voteRepo;
	
	@Autowired
	private CommentRepository comRepo;
	
	@Autowired
	private ProfileRepository profRepo;

	@Override
	public List<Vote> findAllVotesByCommentId(Integer id) {
		return voteRepo.findByCommentId(id);
		
	}

	@Override
	public Vote findOneVoteOnComment(Integer profId, Integer comId) {
		return voteRepo.findByCommentIdAndProfileId(comId, profId);
		
	}

	@Override
	public Vote createVote(Vote vote, Integer cid, String username) {
		VoteKey vk = new VoteKey();
		Profile prof = profRepo.queryByUsernameWithUser(username);
		Comment comm = comRepo.findById(cid).get();
		vk.setComment(comm);
		vk.setProfile(prof);
		
		vote.setId(vk);
		
		return voteRepo.saveAndFlush(vote);
		
	}

	@Override
	public Vote updateVote(Vote vote, Integer vid, Integer cid) {
		Vote nv = voteRepo.findById(vid).get();
		if (nv.getId().getComment().getId() == cid) {
		nv.setVote(vote.getVote());
		}
		return voteRepo.saveAndFlush(nv);
	}

	@Override
	public Boolean deleteAllVotesForComment(Integer cid) {
		return voteRepo.deleteVotesForCommentByCommentId(cid);
		
	}

}
