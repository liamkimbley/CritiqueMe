package com.skilldistillery.critique.services;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.critique.entities.Category;
import com.skilldistillery.critique.entities.Comment;
import com.skilldistillery.critique.entities.Post;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.entities.Vote;
import com.skilldistillery.critique.repositories.CategoryRepository;
import com.skilldistillery.critique.repositories.CommentRepository;
import com.skilldistillery.critique.repositories.PostRepository;
import com.skilldistillery.critique.repositories.ProfileRepository;
import com.skilldistillery.critique.repositories.VoteRepository;

@Transactional
@Service
public class PostServiceImpl implements PostService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private ProfileRepository profRepo;

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private CommentRepository comRepo;

	@Autowired
	private VoteRepository voteRepo;

	@Override
	public List<Post> findPostsByCategoryId(Integer id) {
		Category cat = catRepo.findById(id).get();
		List<Post> posts = postRepo.queryForPostsByCategory(cat);
		if (posts.isEmpty()) {
			return null;
		}
		return posts;

	}

	@Override
	public List<Post> findPostsByCreateDate(LocalDate date) {
		List<Post> posts = postRepo.findByCreatedDate(date);
		if (posts.isEmpty()) {
			return null;
		}
		return posts;

	}

	@Override
	public Set<Post> findAllPosts() {
		Set<Post> posts = postRepo.queryForPostsWithCategories();
		if (posts.isEmpty()) {
			return null;
		}
		Iterator<Post> it = posts.iterator();
		while (it.hasNext()) {
			List<Comment> comments = comRepo.findByPostId(it.next().getId());
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
			} 
		}
		return posts;

	}

	@Override
	public List<Post> findByTitle(String title) {
		List<Post> posts = postRepo.findByTitleContaining(title);
		if (posts.isEmpty()) {
			return null;
		}
		return posts;
	}

	@Override
	public Post findPostById(Integer id) {
		Post p = postRepo.queryForPostWithCommentsByPostId(id);

		if (p != null) {
			return p;
		}

		return null;
	}

	@Override
	public Post create(Post post, String username) {
		Post p = new Post();
		Profile prof = profRepo.queryByUsernameWithUser(username);
		if (prof != null) {

			if (post != null) {
				if (post.getContent() != null && !post.getContent().equals("")) {
					p.setContent(post.getContent());
				}
				if (post.getTitle() != null && !post.getTitle().equals("")) {
					p.setTitle(post.getTitle());
				}
				if (post.getMedia() != null && !post.getMedia().equals("")) {
					p.setMedia(post.getMedia());
				}
				if (post.getCategories() != null) {
					p.setCategories(post.getCategories());
				}
				if (post.getCategories() == null) {
					Category cat = em.find(Category.class, 1);
					p.addCategory(cat);
				}

				p.setProfile(prof);
			}
		}
		return postRepo.saveAndFlush(p);

	}

	@Override
	public Post update(Integer id, Post post) {
		Optional<Post> op = postRepo.findById(id);

		if (op.isPresent()) {
			Post p = op.get();
			if (post.getContent() != null && !post.getContent().equals("")) {
				p.setContent(post.getContent());
			}
			if (post.getProfile() != null) {
				p.setProfile(post.getProfile());
			}
			if (post.getMedia() != null && !post.getMedia().equals("")) {
				p.setMedia(post.getMedia());
			}
			if (post.getTitle() != null && !post.getTitle().equals("")) {
				p.setTitle(post.getTitle());
			}
			return postRepo.saveAndFlush(p);
		}
		return null;

	}

	@Override
	public Post replace(Integer id, Post post) {
		Optional<Post> op = postRepo.findById(id);

		if (op.isPresent()) {
			Post p = op.get();
			if (post.getContent() != null && !post.getContent().equals("")) {
				p.setContent(post.getContent());
			}
			if (post.getProfile() != null) {
				p.setProfile(post.getProfile());
			}
			if (post.getMedia() != null && !post.getMedia().equals("")) {
				p.setMedia(post.getMedia());
			}
			if (post.getTitle() != null && !post.getTitle().equals("")) {
				p.setTitle(post.getTitle());
			}
			return postRepo.saveAndFlush(p);
		}
		return null;

	}

	@Override
	public boolean delete(Integer id) {
		try {
			List<Comment> coms = comRepo.findByPostId(id);
			for (int i = 0; i < coms.size(); i++) {
				voteRepo.deleteVotesForCommentsByCommentId(coms.get(i).getId());
			}
			postRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Post> findPostsByProfileId(Integer pid) {
		return postRepo.findByProfileId(pid);

	}

}
