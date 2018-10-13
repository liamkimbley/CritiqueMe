package com.skilldistillery.critique.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.critique.entities.Vote;
import com.skilldistillery.critique.services.VoteService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"})
public class VoteController {
//	private String username = "test"; // change to principal
	
	@Autowired
	private VoteService vs;
	
	@RequestMapping(path="comments/{cid}/votes", method=RequestMethod.GET)
	public List<Vote> findAllVotesForComment(@PathVariable Integer cid) {
		return vs.findAllVotesByCommentId(cid);
	}
	
	@RequestMapping(path="comments/{cid}/votes", method=RequestMethod.POST)
	public Vote createVote(@PathVariable Integer cid, @RequestBody Vote vote, Principal principal) {
		return vs.createVote(vote, cid, principal.getName());
	}

	@RequestMapping(path="comments/{cid}/votes/{vid}", method=RequestMethod.PUT)
	public Vote updateVote(@PathVariable Integer cid, @PathVariable Integer vid, @RequestBody Vote vote) {
		return vs.updateVote(vote, vid, cid);
	}
}
