import { CategoryService } from './../category.service';
import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { PostService } from '../post.service';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  // User can see posts but cannot open them to view comments
  // When they click a comment, ask them to sign in or sign up
  title = 'CritiqueMe';
  posts: Post[] = [];
  selected: Post = null;
  newPost: Post = new Post();
  editPost: Post = null;
  selectedCategory = this.categoryService.getSelectedCategory();

  reload = function() {
    this.postService.index().subscribe(
      data => {
        console.log(data);
        this.posts = data;
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  displaySelected = function(post: Post) {
    this.selected = post;
  };

  displayPost = function(post: Post) {
    this.selected = post;
    console.log(post);
  };

  goBack = function() {
    this.selected = null;
  };

  addPost = function() {
    this.postService.create(this.newPost).subscribe(
      data => {
        this.reload();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
    console.log(this.newPost);
    this.newPost = new Post();
  };

  setEditPost = function() {
    this.editPost = Object.assign({}, this.selected);
  };

  updatePost = function(post: Post) {
    console.log(post);
    this.postService.update(post).subscribe(
      data => {
        this.selected = data;
        this.editPost = null;
        this.reload();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  deletePost = function(id: number) {
    this.postService.destroy(id).subscribe(
      data => {
        this.reload();
      },
      err => {
        console.error('Observer got an error: ' + err.status);
      }
    );
  };

  constructor(
    private postService: PostService,
    private datePipe: DatePipe,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.reload();
  }
}
