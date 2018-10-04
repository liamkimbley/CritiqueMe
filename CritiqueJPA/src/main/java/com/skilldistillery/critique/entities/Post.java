package com.skilldistillery.critique.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	private String content;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDate updatedDate;
	
	@ManyToMany
	@JoinTable(name="post_category",
	joinColumns = @JoinColumn(name="post_id"),
	inverseJoinColumns = @JoinColumn(name="category_id"))
	private List<Category> categories;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;

	public Post() {
	}

	public Post(int id, Profile profile, String content, LocalDate createdDate, LocalDate updatedDate) {
		super();
		this.id = id;
		this.profile = profile;
		this.content = content;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addCategory(Category category) {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		if (!categories.contains(category)) {
			categories.add(category);
			category.addPost(this);
		}
	}
	
	public void removeCategory(Category category) {
		if (categories != null && categories.contains(category)) {
			categories.remove(category);
			category.removePost(this);
			
		}
	}
	
	public void addComment(Comment comment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		if (!comments.contains(comment)) {
			comments.add(comment);
			if (comment.getPost() != null) {
				comment.getPost().getComments().remove(comment);
			}
			comment.setPost(this);
		}
	}

	public void removeComment(Comment comment) {
		comment.setPost(null);
		if (this.comments != null) {
			comments.remove(comment);
		}
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

}
