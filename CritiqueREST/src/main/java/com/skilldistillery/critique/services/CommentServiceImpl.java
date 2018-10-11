package com.skilldistillery.critique.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.critique.entities.Comment;
import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.Vote;
import com.skilldistillery.critique.repositories.CommentRepository;
import com.skilldistillery.critique.repositories.PostRepository;
import com.skilldistillery.critique.repositories.ProfileRepository;
import com.skilldistillery.critique.repositories.VoteRepository;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository comRepo;
	@Autowired
	private PostRepository postRepo;

	@Autowired
	private ProfileRepository profRepo;
	
	@Autowired
	private VoteRepository voteRepo;

	@Override
	public List<Comment> findCommentsByPost(Integer id) {
		List<Comment> comments = comRepo.findByPostId(id);
		if (!comments.isEmpty()) {
			for (int i = 0; i < comments.size(); i++) {
				int totalPoints = 0;
				List<Vote> votes = voteRepo.findByCommentId(comments.get(i).getId());
				for (int j = 0; j < votes.size(); j++) {
					if (votes.get(j).getVote() == true) {
						totalPoints += 1;
					}
					if (votes.get(j).getVote() == false) {
						totalPoints -= 1;
					}
				}
				comments.get(i).setTotalPoints(totalPoints);
			}
			return comments;
		}
		return null;

	}

	@Override
	public Comment createNewCommentOnPost(Integer id, Comment comment, String username) {
		Comment com = new Comment();
		if (comment.getContent() != null && !comment.getContent().equals("")) {
			com.setContent(comment.getContent());
		}
		if (comment.getPost() != null) {
			com.setPost(comment.getPost());
		}
		if (comment.getPost() == null) {
			Optional<Post> op = postRepo.findById(id);
			if (op.isPresent()) {
				com.setPost(op.get());
			}
		}
		if (comment.getProfile() == null) {
			Profile pr = profRepo.queryByUsernameWithUser(username);
			if (pr != null) {
				com.setProfile(pr);
			}
		}
		if (comment.getProfile() != null) {
			com.setProfile(comment.getProfile());
		}
		return comRepo.saveAndFlush(com);
	}

	@Override
	public Boolean deleteCommentById(Integer cid) {
		Comment toDelete = comRepo.findById(cid).get();
		if (toDelete != null) {
			try {
				voteRepo.deleteVotesForCommentsByCommentId(cid);
				comRepo.deleteById(cid);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Comment findByCommentId(Integer id) {
		Optional<Comment> c = comRepo.findById(id);

		if (c.isPresent()) {
			return c.get();
		}
		return null;
	}

	@Override
	public Comment update(Integer id, Comment com) {
		Optional<Comment> oc = comRepo.findById(id);

		if (oc.isPresent()) {
			Comment c = oc.get();
			if (com.getContent() != null && !com.getContent().equals("")) {
				c.setContent(com.getContent());
			}
			return comRepo.saveAndFlush(c);
		}
		return null;

	}

}
