import { PostService } from './../post.service';
import { Component, OnInit } from '@angular/core';
import { CommentService } from '../comment.service';
import { Post } from '../models/post';
import { Comment } from '../models/comment';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  title = 'CritiqueMe';
  posts: Post[] = [];
  comments: Comment[] = [];
  selectedPost: Post = null;
  selectedComment: Comment = null;
  newComment: Comment = new Comment();
  editComment: Comment = null;

  constructor(private commentService: CommentService, private postService: PostService) { }

  ngOnInit() {
  }

  reload = function() {
    this.commentService.index().subscribe(
      data => { this.comments = data; },
      err => {console.error('Observer got an error: ' + err.status); }
      );
  };

  displayTable = function() {
    this.selected = null;
  };

  addComment = function(id: number) {
    this.commentService.create(this.newComment).subscribe(
      data => {this.reload(); },
      err => {console.error('Observer got an error: ' + err.status); }
      );
      console.log(this.newComment);
      this.newComment = new Comment();
  };

  setEditComment = function() {
    this.editComment = Object.assign({}, this.selected);
  };

  updateComment = function(comment: Comment) {
    console.log(comment);
    this.commentService.update(comment).subscribe(
      data => {
        this.selected = data;
        this.editComment = null;
        this.reload(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

  deleteComment = function(id: number) {
    this.commentService.destroy(id).subscribe(
      data => {
        this.reload(); },
      err => {console.error('Observer got an error: ' + err.status); }
    );
  };

}
