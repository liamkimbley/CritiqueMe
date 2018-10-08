import { PostService } from './../post.service';
import { Component, OnInit } from '@angular/core';
import { CommentService } from '../comment.service';
import { Post } from '../models/post';
import { Comment } from '../models/comment';
import { NgForm } from '@angular/forms';
import { Profile } from '../models/profile';
import { ProfileService } from '../profile.service';

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
  profile: Profile = null;

  constructor(private commentService: CommentService,
              private postService: PostService,
              private profileService: ProfileService) { }

  ngOnInit() {
    this.reload();
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

}
